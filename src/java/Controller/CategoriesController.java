package Controller;

import DAO.CategoryDAO;
import Model.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        CategoryDAO categoryDAO = new CategoryDAO();
        if (action == null) {
            action = "";
        }
        Category category = new Category();

        switch (action) {
            case "request-add":
                rd = request.getRequestDispatcher("Categories/add.jsp");
                rd.forward(request, response);
                break;
            case "add":
                category.setName(request.getParameter("name"));
                category.setDescription(request.getParameter("description"));

                boolean addCategories = categoryDAO.add(category);
                if (addCategories) {
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
                category.setId(Integer.parseInt(String.valueOf(session.getAttribute("id"))));
                category.setName(request.getParameter("name"));
                category.setDescription(request.getParameter("description"));

                boolean editCategories = categoryDAO.edit(category);
                if (editCategories) {
                    request.setAttribute("success", "Categoria editada com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao editar categoria. Tente novamente.");
                }
                rd = request.getRequestDispatcher("categories?action=null");
                rd.forward(request, response);
                break;
            case "delete":
                category.setId(Integer.parseInt(request.getParameter("id")));
                boolean deleteCategories = categoryDAO.delete(category);

                if (deleteCategories) {
                    request.setAttribute("success", "Categoria deletada com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao deletar categoria. Tente novamente.");
                }
                rd = request.getRequestDispatcher("categories?action=null");
                rd.forward(request, response);
                break;
            default:
                List indexCategories = categoryDAO.index();
                request.setAttribute("indexCategories", indexCategories);
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
