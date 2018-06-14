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
<style type="text/css">

    img {
    max-width: 100%;
    max-height: 100%;
}
    
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cantinho do Pito</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    
    <div class="carousel carousel-slider">
        <a class="carousel-item" href="#!"><img src="resources/img/banner.png"></a>
    </div>
    <nav>
        <div class="nav-wrapper black">
            <ul class="brand-logo center hide-on-small-and-down">
                <li><a href="home">Todos</a></li>
                <% List<Category> listCategories = (List<Category>) request.getAttribute("listCategories"); %>
                <% Iterator i = listCategories.iterator(); %>
                <% while (i.hasNext()) { %>
                    <% Category category = (Category) i.next(); %>
                    <li><a href="?category_id=<%= category.getId() %>"><%= category.getName() %></a></li>
                <% } %>
            </ul>
            <form class="right" method="post">
                <div class="input-field">
                    <% if (request.getParameter("search") != null) { %>
                    <input id="search" type="search" name="search" value="<%= request.getParameter("search") %>" placeholder="Pelo que você procura?">
                    <% } else { %>
                    <input id="search" type="search" name="search" placeholder="Pelo que você procura?">
                    <% } %>
                    <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                    <i class="material-icons">close</i>
                    <input type="submit"/>
                </div>
            </form>
            <ul class="right hide-on-med-and-down">
                <li><a href="home?action=checkout"><i class="material-icons">shopping_cart</i></a></li>
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
                            <a href="home?action=view&id=<%= product.getId() %>">
                                <div class="col s12 m4 card-products" style="max-height: 100%">
                                    <div class="card darken-1">
                                        <div class="card-content black-text">
                                            <img height="250" width="250" src="resources/img/Products/<%= product.getImage() %>"/>
                                            <h5><%= product.getName() %></h5>
                                            <small><%= product.getStock() %> produtos no estoque</small>
                                            <h6><%= String.format("R$ %,.2f", product.getPrice()).replace(",", ".") %></h6>
                                        </div>
                                    </div>
                                </div>
                            </a>
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