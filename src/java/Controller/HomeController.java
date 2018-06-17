/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.BuyProduct;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefferson
 */
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private UUID uuid;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        
        RequestDispatcher rd = null;
        CategoryDAO categoryDAO = null;
        ProductDAO productDAO = null;
        Product product = new Product();
        List<Category> listCategories = null;
        List<Product> listProducts = null;
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action  = "";
        }
       
        switch (action) {
            case "view":
                if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
                    rd = request.getRequestDispatcher("home");
                    rd.forward(request, response);
                }
                
                int id = Integer.parseInt(request.getParameter("id"));
                productDAO = new ProductDAO();
                categoryDAO = new CategoryDAO();
                
                product = productDAO.view(id);
                listCategories = categoryDAO.index();
                
                request.setAttribute("listCategories", listCategories);
                request.setAttribute("product", product);
                rd = request.getRequestDispatcher("Home/view.jsp");
                rd.forward(request, response);
                break;
                
            case "add-cart":
                List<BuyProduct> cart = (List<BuyProduct>) request.getSession().getAttribute("cart");
                
                int productId = (int) request.getSession().getAttribute("productId");
                
                int amount = Integer.parseInt(request.getParameter("quantity"));
                if (cart == null || cart.isEmpty()) {
                    
                    cart = new ArrayList<BuyProduct>();
                    productDAO = new ProductDAO();
                    
                    uuid = UUID.randomUUID();
                    BuyProduct buyProduct = new BuyProduct();
                    buyProduct.setBuyId(String.valueOf(uuid));
                    buyProduct.setAmount(amount);
                    buyProduct.setProductId(productId);
                    buyProduct.setProduct(productDAO.view(productId));
                    buyProduct.setTotalPrice(buyProduct.getProduct().getPrice() * amount);
                    
                    cart.add(buyProduct);
                    
                    request.getSession().setAttribute("cart", cart);
                } else {
                    if (checkProductExistsInCart(productId, cart)) {
                        updateAmountBuyProduct(productId, cart, amount);
                    } else {
                        BuyProduct buyProduct = new BuyProduct();
                        productDAO = new ProductDAO();
                        
                        buyProduct.setBuyId(String.valueOf(uuid));
                        buyProduct.setAmount(amount);
                        buyProduct.setProductId(productId);
                        buyProduct.setProduct(productDAO.view(productId));
                        buyProduct.setTotalPrice(buyProduct.getProduct().getPrice() * amount);
                        cart.add(buyProduct);

                        request.getSession().setAttribute("cart", cart);
                    }
                }
                
                response.sendRedirect("buy?action=checkout");
                
                break;
                
            case "checkout":
                categoryDAO = new CategoryDAO();
                
                listCategories = categoryDAO.index();
                request.setAttribute("listCategories", listCategories);
                rd = request.getRequestDispatcher("Buy/checkout.jsp");
                rd.forward(request, response);
                break;
                
            case "buy-less":
                    if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
                        productId = Integer.parseInt(request.getParameter("id"));
                        if (request.getSession().getAttribute("cart") != null) {
                            cart = (List<BuyProduct>) request.getSession().getAttribute("cart");
                        
                            buyLess(productId, cart);
                        }
                    }
                    
                    response.sendRedirect("buy?action=checkout");
                break;
                
            case "buy-more":
                    if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
                        productId = Integer.parseInt(request.getParameter("id"));
                        cart = (List<BuyProduct>) request.getSession().getAttribute("cart");
                        
                        buyMore(productId, cart);
                    }
                    
                    response.sendRedirect("buy?action=checkout");
                break;
                
            default:
                categoryDAO = new CategoryDAO();
                productDAO = new ProductDAO();
                
                listCategories = categoryDAO.index();
                
                if (request.getParameter("category_id") == null) {
                     listProducts = productDAO.index();
                } else {
                     listProducts = productDAO.index(Integer.parseInt(request.getParameter("category_id")));
                }
                
                if (request.getParameter("search") != null) {
                    productDAO = new ProductDAO();
                    listProducts = productDAO.index(request.getParameter("search"));
                    request.setAttribute("search", request.getParameter("search"));
                }
                
                request.setAttribute("listCategories", listCategories);
                request.setAttribute("listProducts", listProducts);
                rd = request.getRequestDispatcher("Home/index.jsp");
                rd.forward(request, response);
                
                break;
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

    private boolean checkProductExistsInCart(int productId, List<BuyProduct> cart)
    {
        Iterator<BuyProduct> i = cart.iterator();
        while (i.hasNext()) {
            BuyProduct buyProduct = i.next();
            if (buyProduct.getProductId() == productId) {
                return true;
            }
        }
        
        return false;
    }
    
    private List<BuyProduct> updateAmountBuyProduct(int productId, List<BuyProduct> cart, int amount)
    {
        Iterator<BuyProduct> i = cart.iterator();
        while (i.hasNext()) {
            BuyProduct buyProduct = i.next();
            if (buyProduct.getProductId() == productId) {
                buyProduct.setAmount(buyProduct.getAmount() + amount);
                buyProduct.setTotalPrice(buyProduct.getProduct().getPrice() * buyProduct.getAmount());
            }
        }
        
        return cart;
    }
    
    private void showCart(List<BuyProduct> cart)
    {
        Iterator<BuyProduct> i = cart.iterator();
        
        while (i.hasNext()) {
            BuyProduct buyProduct = i.next();
        }
    }
    
    private List<BuyProduct> buyLess(int productId, List<BuyProduct> cart)
    {
        Iterator<BuyProduct> i = cart.iterator();
        
        while (i.hasNext()) {
            BuyProduct buyProduct = i.next();

            if (buyProduct.getProductId() == productId) {
                if (buyProduct.getAmount() == 1) {
                    cart.remove(buyProduct);
                    break;
                } else {
                    buyProduct.setAmount(buyProduct.getAmount() - 1);
                    buyProduct.setTotalPrice(buyProduct.getAmount() * buyProduct.getProduct().getPrice());
                }
            }
        }
        
        return cart;
    }
    
    private List<BuyProduct> buyMore(int productId, List<BuyProduct> cart)
    {
        Iterator<BuyProduct> i = cart.iterator();
        
        while (i.hasNext()) {
            BuyProduct buyProduct = i.next();
            
            if (buyProduct.getProductId() == productId) {
                if (buyProduct.getAmount() < buyProduct.getProduct().getStock()) {
                    buyProduct.setAmount(buyProduct.getAmount() + 1);
                    buyProduct.setTotalPrice(buyProduct.getAmount() * buyProduct.getProduct().getPrice());
                }
            }
        }
        
        return cart;
    }
    
}
