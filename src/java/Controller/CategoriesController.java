package Controller;

import DAO.CategoryDAO;
import Model.Category;
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
public class CategoriesController extends HttpServlet {

    private Category category;
    private CategoryDAO categoryDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        categoryDAO = new CategoryDAO();
        category = new Category();

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "request-add":
                rd = request.getRequestDispatcher("Categories/add.jsp");
                rd.forward(request, response);
                break;

            case "add":
                category.setName(request.getParameter("name"));
                category.setDescription(request.getParameter("description"));

                if (categoryDAO.add(category)) {
                    request.setAttribute("success", "Categoria cadastrada com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao casdastrar categoria. Tente novamente.");
                }
                rd = request.getRequestDispatcher("categories?action=null");
                rd.forward(request, response);
                break;

            case "request-edit":
                category = categoryDAO.view(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("categoryEdit", category);
                rd = request.getRequestDispatcher("Categories/edit.jsp");
                rd.forward(request, response);
                break;

            case "edit":
                HttpSession session = request.getSession();
                if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
                    rd = request.getRequestDispatcher("clients?action=null");
                    rd.forward(request, response);
                }
                category.setId(Integer.parseInt(String.valueOf(session.getAttribute("id"))));
                category.setName(request.getParameter("name"));
                category.setDescription(request.getParameter("description"));

                if (categoryDAO.edit(category)) {
                    request.setAttribute("success", "Categoria editada com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao editar categoria. Tente novamente.");
                }
                rd = request.getRequestDispatcher("categories?action=null");
                rd.forward(request, response);
                break;

            case "delete":
                category.setId(Integer.parseInt(request.getParameter("id")));

                if (categoryDAO.delete(category)) {
                    request.setAttribute("success", "Categoria excluída com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao excluir categoria. Tente novamente.");
                }

                rd = request.getRequestDispatcher("categories?action=null");
                rd.forward(request, response);
                break;
            default:
                List<Category> listCategories = categoryDAO.index();
                request.setAttribute("listCategories", listCategories);
                rd = request.getRequestDispatcher("Categories/index.jsp");
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
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
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
