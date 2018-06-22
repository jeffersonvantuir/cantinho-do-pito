/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BrandDAO;
import Model.Brand;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ricardo
 */
public class BrandsController extends HttpServlet {

    private Brand brand;
    private BrandDAO brandDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        brandDAO = new BrandDAO();
        brand = new Brand();

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "request-add":
                rd = request.getRequestDispatcher("Brands/add.jsp");
                rd.forward(request, response);
                break;

            case "add":
                brand.setName(request.getParameter("name"));

                if (brandDAO.add(brand)) {
                    request.setAttribute("success", "Marca cadastrada com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao casdastrar marca. Tente novamente.");
                }
                rd = request.getRequestDispatcher("brands?action=null");
                rd.forward(request, response);
                break;

            case "request-edit":
                brand = brandDAO.view(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("brandEdit", brand);
                rd = request.getRequestDispatcher("Brands/edit.jsp");
                rd.forward(request, response);
                break;

            case "edit":
                HttpSession session = request.getSession();
                if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
                    rd = request.getRequestDispatcher("clients?action=null");
                    rd.forward(request, response);
                }
                brand.setId(Integer.parseInt(String.valueOf(session.getAttribute("id"))));
                brand.setName(request.getParameter("name"));

                if (brandDAO.edit(brand)) {
                    request.setAttribute("success", "Marca editada com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao editar marca. Tente novamente.");
                }
                rd = request.getRequestDispatcher("brands?action=null");
                rd.forward(request, response);
                break;

            case "delete":
                brand.setId(Integer.parseInt(request.getParameter("id")));
                if (brandDAO.delete(brand)) {
                    request.setAttribute("success", "Marca excluída com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao excluir marca. Tente novamente.");
                }
                rd = request.getRequestDispatcher("brands?action=null");
                rd.forward(request, response);
                break;
            default:
                List <Brand> listBrands = brandDAO.index();
                request.setAttribute("listBrands", listBrands);
                rd = request.getRequestDispatcher("Brands/index.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BrandsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BrandsController.class.getName()).log(Level.SEVERE, null, ex);
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
