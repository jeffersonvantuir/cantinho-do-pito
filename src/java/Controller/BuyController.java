/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AddressDAO;
import DAO.BuyDAO;
import DAO.BuyProductDAO;
import DAO.CategoryDAO;
import DAO.CityDAO;
import DAO.ProductDAO;
import DAO.StateDAO;
import Helpers.Helper;
import Model.Address;
import Model.Buy;
import Model.BuyProduct;
import Model.Category;
import Model.City;
import Model.Client;
import Model.Product;
import Model.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefferson
 */
public class BuyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        
        RequestDispatcher rd = null;
        CategoryDAO categoryDAO = null;
        ProductDAO productDAO = null;
        Product product = new Product();
        List<Category> listCategories = null;
        List<Product> listProducts = null;
        
        if (request.getSession().getAttribute("cart") == null) {
            response.sendRedirect("home");
        } else {
            String action = request.getParameter("action");
        
            if (action == null) {
                action  = "";
            }

            switch (action) {
                case "request-checkout":
                    categoryDAO = new CategoryDAO();

                    listCategories = categoryDAO.index();
                    request.setAttribute("listCategories", listCategories);
                    rd = request.getRequestDispatcher("Buy/checkout.jsp");
                    rd.forward(request, response);
                    break;

                case "complete-purchase":
                    if (request.getSession().getAttribute("client") == null || request.getSession().getAttribute("client").equals("")) {

                        request.getSession().setAttribute("complete_purchase", true);
                        response.sendRedirect("clients?action=request-login");
                    } else {
                        categoryDAO = new CategoryDAO();
                        listCategories = categoryDAO.index();
                        CityDAO cityDAO = new CityDAO();
                        StateDAO stateDAO = new StateDAO();

                        List listStates = stateDAO.list();

                        request.setAttribute("listCategories", listCategories);
                        request.setAttribute("listStates", listStates);
                        rd = request.getRequestDispatcher("Buy/complete_purchase.jsp");
                        rd.forward(request, response);    
                    }
                    break;
                    
                case "checkout":
                    if (request.getParameter("option") != null) {
                        Address address = new Address();
                        UUID uuid = UUID.randomUUID();
                        
                        address.setId(String.valueOf(uuid));
                        address.setAddress(request.getParameter("address"));
                        address.setComplement(request.getParameter("complement"));
                        address.setDistrict(request.getParameter("district"));
                        
                        if (request.getParameter("home_number").equals("")) {
                            address.setHomeNumber(0);
                        } else {
                            address.setHomeNumber(Integer.parseInt(request.getParameter("home_number")));
                        }
                        
                        address.setZipcode(request.getParameter("zipcode"));
                        address.setCityId(Integer.parseInt(request.getParameter("city")));
                        
                        saveBuy(address, request, response);
                        
                    } else {
                        Address address = new Address();
                        UUID uuid = UUID.randomUUID();
                        Client client = (Client) request.getSession().getAttribute("client");
                        
                        address.setId(String.valueOf(uuid));
                        address.setAddress(client.getAddress());
                        address.setComplement(client.getComplement());
                        address.setDistrict(client.getDistrict());
                        address.setHomeNumber(client.getHomeNumber());
                        address.setZipcode(client.getZipcode());
                        address.setCityId(client.getCityId());
                        
                        saveBuy(address, request, response);
                    }
                    break;
                    
                case "load-address-form":
                    State state = new State();
                    CityDAO cityDAO = new CityDAO();
                    
                    state.setId(1);
                    StateDAO stateDAO = new StateDAO();
                    List<State> listStates = stateDAO.list();
                    
                    request.setAttribute("listStates", listStates);
                    rd = request.getRequestDispatcher("Buy/address.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    response.sendRedirect("home");
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void saveBuy(Address address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher rd = null;
        AddressDAO addressDAO = new AddressDAO();
        boolean successfully = true;

        if (addressDAO.add(address)) {
            BuyDAO buyDAO = new BuyDAO();
            Client client = (Client) request.getSession().getAttribute("client");

            List<BuyProduct> cart = (List<BuyProduct>) request.getSession().getAttribute("cart");
            Iterator<BuyProduct> i = cart.iterator();

            double totalPrice = 0;

            Buy buy = new Buy();

            while (i.hasNext()) {
                BuyProduct buyProduct = i.next();
                Date date = new Date();                                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                buy.setId(buyProduct.getBuyId());
                buy.setClientId(client.getId());
                buy.setAddressId(address.getId());
                buy.setDate(dateFormat.format(date));

                totalPrice += buyProduct.getTotalPrice();

                BuyProductDAO buyProductDAO = new BuyProductDAO();

                if (buyDAO.buyExists(buyProduct.getBuyId())) {

                    if (buyProductDAO.add(buyProduct)) {
                        if (!buyDAO.updateTotalPrice(buy.getId(), totalPrice)) {
                            successfully = false;
                            request.setAttribute("error", "Falha ao atualizar preço total da compra");
                            break;
                        }
                    } else {
                        successfully = false;
                        request.setAttribute("error", "Falha ao cadastrar o item da compra");
                        response.sendRedirect("home");
                        break;
                    }
                } else {
                    buy.setTotalPrice(totalPrice);
                    if (buyDAO.add(buy)) {
                        if (buyProductDAO.add(buyProduct)) {
                            if (!buyDAO.updateTotalPrice(buy.getId(), totalPrice)) {
                                successfully = false;
                                request.setAttribute("error", "Falha ao atualizar preço total da compra");
                                break;
                            }
                        } else {
                            successfully = false;
                            request.setAttribute("error", "Falha ao cadastrar o item da compra");
                            rd = request.getRequestDispatcher("home?action=index");
                            rd.forward(request, response);
                            break;
                        }
                    } else {
                        successfully = false;
                        request.setAttribute("error", "Falha ao cadastrar compra");
                        rd = request.getRequestDispatcher("home?action=index");
                        rd.forward(request, response);
                        break;
                    }
                }
            }
        } else {
            request.getSession().setAttribute("cart", null);
            request.setAttribute("error", "Falha ao cadastrar endereço");
            rd = request.getRequestDispatcher("home?action=index");
            rd.forward(request, response);
        }

        if (successfully) {
            request.getSession().setAttribute("cart", null);
            request.setAttribute("success", "Compra realizada com sucesso. Acesse 'Meus pedidos' para acompanhar.");
            rd = request.getRequestDispatcher("home?action=index");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("cart", null);
            request.setAttribute("error", "Ocorreu um erro ao finalizar compra");
            rd = request.getRequestDispatcher("home?action=index");
            rd.forward(request, response);
        }
    }
}
