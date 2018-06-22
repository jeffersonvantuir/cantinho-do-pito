
package Controller;

import DAO.BuyDAO;
import DAO.ProductDAO;
import DAO.RequestDAO;
import Model.Product;
import Model.Request;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ricardo
 */
public class DashboardController extends HttpServlet {

    private Request requests;
    private RequestDAO requestDAO;
    private Product product;
    private ProductDAO productDAO;
    private BuyDAO buyDAO;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String action = request.getParameter("action");
            RequestDispatcher rd = null;
            requestDAO = new RequestDAO();
            requests =  new Request();
            product = new Product();
            productDAO = new ProductDAO();
            buyDAO = new BuyDAO();
            if (action == null) {
                action = "";
            }
            
            switch (action) {
                default:
                    request.setAttribute("allRequests", requestDAO.getAllRequests());
                    request.setAttribute("lowStock", productDAO.lowStock());
                    request.setAttribute("totalMonthlyAmount", buyDAO.getTotalMonthlyAmount());
                    request.setAttribute("totalMonthlyPurchases", buyDAO.getTotalMonthlyPurchases());
                    request.setAttribute("totalPurchasesToAccept", buyDAO.getTotalPurchasesToAccept());
                    rd = request.getRequestDispatcher("Dashboard/index.jsp");
                    rd.forward(request, response);
                    break;
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
