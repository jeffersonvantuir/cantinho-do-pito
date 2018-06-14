/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;
import java.io.IOException;
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

}
