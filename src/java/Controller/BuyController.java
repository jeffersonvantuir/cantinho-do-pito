/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.CityDAO;
import DAO.ProductDAO;
import DAO.StateDAO;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
                case "checkout":
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

}
