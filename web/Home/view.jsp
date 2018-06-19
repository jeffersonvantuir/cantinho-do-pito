<%-- 
    Document   : view
    Created on : 13/06/2018, 20:55:15
    Author     : jefferson
--%>

<%@page import="Model.BuyProduct"%>
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
        <a class="carousel-item" href="#!"><img src="resources/img/banner.png"></a>
    </div>
    <jsp:include page="../Layout/navbar_client.jsp"/>
    <body>
        <main>
            <div class="row">
              <div class="col s12 m10 offset-m1">
                <div class="card darken-1">
                  <div class="card-content black-text">
                    <span class="card-title black-text"></span>
                    <div class="row">
                        <% Product product = (Product) request.getAttribute("product"); %>
                        <div class="col s12 m6">
                            <img class="materialboxed" height="350" width="350" src="resources/img/Products/<%= product.getImage() %>"/>
                        </div>
                        <div class="col s12 m6">
                            <% session.setAttribute("productId", product.getId()); %>
                            <h5><%= product.getName() %></h5>
                            <% if (product.getStock() > 0) { %>
                                <p><%= product.getStock() %> produtos no estoque</p>
                            <% } else { %>
                                <p class="red-text">Produto indisponível no momento</p>
                            <% } %>
                            <h4><%= String.format("R$ %,.2f", product.getPrice()).replace(",", ".") %></h4>
                            <% if (product.getStock() > 0) { %>
                                <div class="row">
                                    <div class="input-field col s12 m7">
                                        <form method="post" id="form" action="home?action=add-cart">
                                            <input type="number" id="quantity" name="quantity" class="validate" value="1" min="1" max="<%= product.getStock() %>"/>
                                            <span class="helper-text" data-error="Selecione um valor entre 1 e <%= product.getStock() %>"></span><br>
                                        </form>
                                    </div>
                                    <a id="add-cart" class="waves-effect waves-light btn amber lighten-1 col s12 m7">
                                        <i class="material-icons right">add_shopping_cart</i>
                                        Adicionar ao carrinho
                                    </a>
                                </div>
                            <% } else { %>
                                <% if (request.getSession().getAttribute("client") != null) { %>
                                    <a href="requests?action=notify-me&product_id=<%= product.getId() %>" class="waves-effect waves-light btn amber lighten-1 col s12 m7">
                                        <i class="material-icons left">notification_important</i>
                                        Avisar-me quando disponível
                                    </a>
                                <% } else { %>        
                                    <p class="red-text">Para solicitar o produto, faça login</p>
                                <% } %>
                            <% } %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12 m10 offset-m1">
                            <h5>Informações do Produto</h5><br>
                            <p><b>Marca: </b><%= product.getBrand().getName() %></p><br>
                            <p><b>Categoria: </b><%= product.getCategory().getName() %></p><br>
                            <p><b>Preço: </b><%= String.format("R$ %,.2f", product.getPrice()).replace(",", ".") %></p><br>
                            <p style="text-align: justify; text-justify: inter-ideograph; text-indent: 50px"><%= product.getDescription() %></p>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>
<script>
    $('#quantity').on('input', function() {
          this.value = this.value
            .replace(/[^\d.]/g, '')             // numbers and decimals only
        });
        
    $("#add-cart").click(function() {
        var max = <%= product.getStock() %>;
        var value = $("#quantity").val();
        
        if (value >= 1 && value <= max) {
            form = document.getElementById("form");
            form.submit();
        }
    });
</script>