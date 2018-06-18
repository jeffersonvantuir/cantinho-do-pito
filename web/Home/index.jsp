<%-- 
    Document   : index
    Created on : 13/06/2018, 14:23:45
    Author     : jefferson
--%>

<%@page import="Model.BuyProduct"%>
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
    <jsp:include page="../Layout/navbar_client.jsp"/>
    <body>
        <main>
            <div class="row">
            <jsp:include page="../Layout/flash.jsp"/>
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
                                            <h5 title="<%= product.getName() %>" class="truncate"><%= product.getName() %></h5>
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