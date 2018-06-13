<%-- 
    Document   : index
    Created on : 13/06/2018, 14:23:45
    Author     : jefferson
--%>

<%@page import="Model.Product"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Model.Category"%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cantinho do Pito</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    
    <div class="carousel carousel-slider">
        <a class="carousel-item" href="#!"><img height="100%" width="100%" src="resources/img/banner.png"></a>
    </div>
    <nav>
        <div class="nav-wrapper black">
            <ul class="brand-logo center hide-on-small-and-down">
                <% List<Category> listCategories = (List<Category>) request.getAttribute("listCategories"); %>
                <% Iterator i = listCategories.iterator(); %>
                <% while (i.hasNext()) { %>
                    <% Category category = (Category) i.next(); %>
                    <li><a href="#!"><%= category.getName() %></a></li>
                <% } %>
            </ul>
        </div>
    </nav>
    <body>
        <main>
            <div class="row">
              <div class="col s12 m10 offset-m1">
                <div class="card darken-1">
                  <div class="card-content black-text">
                    <span class="card-title black-text"></span>
                    <div class="row">
                        <% List<Product> listProducts = (List<Product>) request.getAttribute("listProducts"); %>
                        <% Iterator in = listProducts.iterator(); %>
                        <% while (in.hasNext()) { %>
                            <% Product product = (Product) in.next(); %>
                        <div class="col s12 m3 card-products">
                            <div class="card darken-1">
                                <div class="card-content black-text">
                                    <img class="materialboxed" height="300" width="300" src="resources/img/Products/<%= product.getImage() %>"/>
                                    <h5><%= product.getName() %></h5>
                                    <small><%= product.getStock() %> produtos no estoque</small>
                                    <h6><%= String.format("R$ %,.2f", product.getPrice()).replace(",", ".") %></h6>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>