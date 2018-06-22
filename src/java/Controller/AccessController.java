package Controller;

import Model.Client;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class AccessController extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "logout":
                request.getSession().getAttribute("client");
                logout(request, response);
                break;
        }
    }

    public void accessControl(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        Client client = (Client) request.getSession().getAttribute("client");
        RequestDispatcher rd = null;
        if (client == null) {
            rd = request.getRequestDispatcher("/clients?action=request-login");
            request.setAttribute("error", "Acesso não autorizado.");
            rd.forward(request, response);
        } else {
            if (!client.isIsAdmin()) {
                rd = request.getRequestDispatcher("/clients?action=request-login");
                request.setAttribute("error", "Acesso não autorizado.");
                rd.forward(request, response);
            }
        }
    }

    public void timeOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        Client client = (Client) request.getSession().getAttribute("client");
        RequestDispatcher rd = null;
        if (client == null) {
            rd = request.getRequestDispatcher("/clients?action=request-login");
            request.setAttribute("error", "Sua sessão expirou.");
            rd.forward(request, response);
        } else {
            request.getSession().setMaxInactiveInterval(1800);
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        Client client = (Client) request.getSession().getAttribute("client");
        RequestDispatcher rd = null;
        if (client != null) {
            request.getSession().invalidate();
            request.setAttribute("success", "Logout.");
            rd = request.getRequestDispatcher("/clients?action=request-login");
            rd.forward(request, response);
        }
    }

    public void directoryControl(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        RequestDispatcher rd = null;
        request.setAttribute("error", "Diretório inválido.");
        rd = request.getRequestDispatcher("/clients?action=request-login");
        rd.forward(request, response);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccessController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccessController.class.getName()).log(Level.SEVERE, null, ex);
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
