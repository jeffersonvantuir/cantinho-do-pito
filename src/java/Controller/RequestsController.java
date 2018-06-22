package Controller;

import DAO.BuyDAO;
import DAO.BuyProductDAO;
import DAO.CategoryDAO;
import DAO.RequestDAO;
import Model.Buy;
import Model.BuyProduct;
import Model.Category;
import Model.Client;
import Model.Request;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class RequestsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        BuyDAO buyDAO = null;
        CategoryDAO categoryDAO = null;
        RequestDAO requestDAO = null;
        
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "my-requests":
                buyDAO = new BuyDAO();
                categoryDAO = new CategoryDAO();
                requestDAO = new RequestDAO();
                
                if (request.getSession().getAttribute("client") != null) {
                    Client client = (Client) request.getSession().getAttribute("client");
                
                    List<Buy> listBuy = buyDAO.getClientRequests(client.getId());
                    List<Category> listCategories = categoryDAO.index();
                    List<Request> listRequests = requestDAO.getClientsRequests(client.getId());
                    
                    request.setAttribute("listRequests", listRequests);
                    request.setAttribute("listCategories", listCategories);
                    request.setAttribute("listBuy", listBuy);
                    rd = request.getRequestDispatcher("Requests/my_requests.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("home");
                }
                
                break;
                
            case "notify-me":
                int productId = Integer.parseInt(request.getParameter("product_id"));
                Client client = (Client) request.getSession().getAttribute("client");
                
                requestDAO = new RequestDAO();
                
                Request r = new Request();
                r.setClientId(client.getId());
                r.setProductId(productId);
                
                if (requestDAO.add(r)) {
                    request.setAttribute("success", "Sua solicitação foi cadastrada");
                    response.sendRedirect("home");
                }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RequestsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RequestsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
