/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Helpers.Helper;
import Model.Brand;
import Model.Category;
import Model.Product;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author jefferson
 */
public class ProductsController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=ISO-8859-1");
        
        RequestDispatcher rd = null;
        CategoryDAO categoryDAO = null;
        Helper helper = new Helper();
        ProductDAO productDAO = null;
        BrandDAO brandDAO = null;
        Product product = new Product();
        List<Brand> listBrands = null;
        List<Category> listCategories = null;
        DiskFileItemFactory factory = null;
        ServletContext servletContext = null;
        File repository = null;
        ServletFileUpload upload = null;
        List<FileItem> items = null;
        Iterator<FileItem> iter = null;
        List<Product> listProducts = null;
        List<Product> listRequests = null;
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "request-add":
                categoryDAO = new CategoryDAO();
                brandDAO = new BrandDAO();
                
                listCategories = categoryDAO.index();
                listBrands = brandDAO.index();
                
                request.setAttribute("listCategories", listCategories);
                request.setAttribute("listBrands", listBrands);
                
                rd = request.getRequestDispatcher("Products/add.jsp");
                rd.forward(request, response);
                break;
                
            case "request-edit":
                if (request.getParameter("id").equals("") || request.getParameter("id") == null) {
                    rd = request.getRequestDispatcher("products");
                    rd.forward(request, response);
                }
                categoryDAO = new CategoryDAO();
                productDAO = new ProductDAO();
                brandDAO = new BrandDAO();
                product = productDAO.view(Integer.parseInt(request.getParameter("id")));
                listCategories = categoryDAO.index();
                listBrands = brandDAO.index();
                
                request.setAttribute("listCategories", listCategories);
                request.setAttribute("listBrands", listBrands);
                request.setAttribute("product", product);
                rd = request.getRequestDispatcher("Products/edit.jsp");
                rd.forward(request, response);
                break;
               
            case "add":
                if (!ServletFileUpload.isMultipartContent(request)) {
                    rd = request.getRequestDispatcher("/products?action=index");
                    rd.forward(request, response);
                }
                
                product = new Product();
                
                factory = new DiskFileItemFactory();
                
                servletContext = this.getServletConfig().getServletContext();
                repository = (File) servletContext.getAttribute("javax.servel.context.tempdir");
                factory.setRepository(repository);
                
                upload = new ServletFileUpload(factory);
                
                items = upload.parseRequest(request);
                
                iter = items.iterator();
                
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    
                    if (item.isFormField()) {
                        product = processFormField(item, product);
                        if (product.getName() == null) {
                            request.setAttribute("error", "Ocorreu um erro durante o processamento dos valores. Tente novamente.");
                            rd = request.getRequestDispatcher("/products?action=index");
                            rd.forward(request, response);
                        }
                        
                    } else {
                        product = processUploadedFile(item, product);
                        
                        if (product.getImage() == null) {
                            request.setAttribute("error", "Falha ao salvar imagem. Tente novamente.");
                            rd = request.getRequestDispatcher("/products?action=index");
                            rd.forward(request, response);
                        }
                    }
                }
                
                productDAO = new ProductDAO();
                
                if (productDAO.add(product)) {
                    request.setAttribute("success", "Produto cadastrado com sucesso.");
                    rd = request.getRequestDispatcher("/products?action=index");
                    rd.forward(request, response);
                    
                } else {
                    request.setAttribute("error", "Falha ao cadastrar produto. Tente novamente.");
                    rd = request.getRequestDispatcher("/products?action=index");
                    rd.forward(request, response);
                }
                
                break;
                
             case "edit":
                 if (request.getSession().getAttribute("id") == null) {
                    rd = request.getRequestDispatcher("/products?action=index");
                    rd.forward(request, response);
                 }

                 if (!ServletFileUpload.isMultipartContent(request)) {
                    System.out.println("Nenhuma imagem selecionada");
                    request.setAttribute("error", "Nenhuma imagem selecionada");
                    rd = request.getRequestDispatcher("/products?action=request-add");
                    rd.forward(request, response);
                }
                
                product = new Product();
                int id = (int) request.getSession().getAttribute("id");
                product.setId(id);
                factory = new DiskFileItemFactory();
                
                servletContext = this.getServletConfig().getServletContext();
                repository = (File) servletContext.getAttribute("javax.servel.context.tempdir");
                factory.setRepository(repository);
                
                upload = new ServletFileUpload(factory);
                
                items = upload.parseRequest(request);
                
                iter = items.iterator();
                
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    
                    if (item.isFormField()) {
                        product = processFormField(item, product);
                        if (product.getName() == null) {
                            request.setAttribute("error", "Ocorreu um erro durante o processamento dos valores. Tente novamente.");
                            rd = request.getRequestDispatcher("/products?action=index");
                            rd.forward(request, response);
                        }
                        
                    } else {
                        if (!item.getString().equals("") && item.getString() != null) {
                            product = processUploadedFile(item, product);
                        
                            if (product.getImage() == null) {
                                request.setAttribute("error", "Falha ao salvar imagem. Tente novamente.");
                                rd = request.getRequestDispatcher("/products?action=index");
                                rd.forward(request, response);
                            }
                        }
                    }
                }
                
                productDAO = new ProductDAO();
                
                if (productDAO.edit(product)) {
                    request.getSession().setAttribute("id", null);
                    request.setAttribute("success", "Produto alterado com sucesso.");
                    rd = request.getRequestDispatcher("/products?action=index");
                    rd.forward(request, response);
                    
                } else {
                    request.getSession().setAttribute("id", null);
                    request.setAttribute("error", "Falha ao alterar produto. Tente novamente.");
                    rd = request.getRequestDispatcher("/products?action=index");
                    rd.forward(request, response);
                }
                break;
            
             case "delete":
                product = new Product();
                product.setId(Integer.parseInt(request.getParameter("id")));
                
                productDAO = new ProductDAO();
                
                if (productDAO.delete(product)) {
                    request.setAttribute("success", "Produto excluído com sucesso.");
                } else {
                    request.setAttribute("error", "Falha ao excluir Produto. Por favor, tente novamente.");
                }
                rd = request.getRequestDispatcher("/products?action=index");
                rd.forward(request, response);
                break;

             case "requests":
                productDAO = new ProductDAO();
                listRequests = productDAO.requests();
                request.setAttribute("listRequests", listRequests);
                rd = request.getRequestDispatcher("Products/requests.jsp");
                rd.forward(request, response);
                break;
                 
             case "low-stock":
                productDAO = new ProductDAO();
                listProducts = productDAO.listLowStock();
                request.setAttribute("listProducts", listProducts);
                rd = request.getRequestDispatcher("Products/low_stock.jsp");
                rd.forward(request, response);
                break;
                
            default:
                productDAO = new ProductDAO();
                listProducts = productDAO.index();
                request.setAttribute("listProducts", listProducts);
                rd = request.getRequestDispatcher("Products/index.jsp");
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
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
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

    private Product processFormField(FileItem item, Product product)
    {
        String fieldName = item.getFieldName();
        String value = item.getString();
        
         switch (fieldName) {
            case "name":
                product.setName(value);
                break;

            case "price":
                product.setPrice(Double.parseDouble(value));
                break;

            case "stock":
                product.setStock(Integer.parseInt(value));
                break;

            case "brand_id":
                product.setBrandId(Integer.parseInt(value));
                break;

            case "category_id":
                product.setCategoryId(Integer.parseInt(value));
                break;

            case "description":
                product.setDescription(value);
                break;
        }
         
         return product;
    }
    
    private Product processUploadedFile(FileItem item, Product product)
    {
        try {
            File uploadDir = new File(getServletContext().getRealPath("/") + "resources/img/Products");
            File file = File.createTempFile("img", ".png", uploadDir);
            item.write(file);
            
            System.out.println("FILENAME: " + file.getName());
        
            product.setImage(file.getName());
            
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return product;
    }
}
