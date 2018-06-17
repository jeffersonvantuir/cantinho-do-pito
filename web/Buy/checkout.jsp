<%-- 
    Document   : checkout
    Created on : 13/06/2018, 23:03:15
    Author     : jefferson
--%>

<%@page import="Model.Category"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Model.BuyProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cantinho do Pito</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar_client.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m10 offset-m1">
                    <div class="card darken-1">
                        <div class="card-content black-text">
                            <span class="card-title black-text">Seu carrinho</span>
                            
                            <% if (request.getSession().getAttribute("cart") != null) { %>
                                <% List<BuyProduct> cart = (List<BuyProduct>) request.getSession().getAttribute("cart"); %>
                                <% if (cart.isEmpty()) { %>
                                <p>
                                    <i class="material-icons black-text">sentiment_very_dissatisfied</i>
                                    Ahh não, seu carrinho está vazio...
                                </p>
                                <% } else { %>
                                <table class="highlight">
                                    <thead>
                                        <tr>
                                            <th>Produto</th>
                                            <th>Categoria</th>
                                            <th>Marca</th>
                                            <th>Quantidade</th>
                                            <th>Imagem</th>
                                            <th>Valor Unitário</th>
                                            <th>Valor Total</th>
                                        </tr>
                                    </thead>
                                    <% double buyTotalPrice = 0; %>
                                    <tbody>
                                <% 
                                    Iterator<BuyProduct> iter = cart.iterator();
                                    
                                    while (iter.hasNext()) {
                                        BuyProduct buyProduct = iter.next();
                                        buyTotalPrice += buyProduct.getTotalPrice();
                                %>      
                                        <tr>
                                            <td><%= buyProduct.getProduct().getName() %></td>
                                            <td><%= buyProduct.getProduct().getCategory().getName() %></td>
                                            <td><%= buyProduct.getProduct().getBrand().getName() %></td>
                                            <td>
                                                <a class="tooltipped" href="home?action=buy-less&id=<%= buyProduct.getProductId() %>" data-position="left" data-tooltip="Comprar menos"><i class="material-icons black-text" title="Comprar menos">keyboard_arrow_left</i></a>
                                                <%= buyProduct.getAmount() %>
                                                <a class="tooltipped" href="home?action=buy-more&id=<%= buyProduct.getProductId() %>" data-position="right" data-tooltip="Comprar mais"><i class="material-icons black-text">keyboard_arrow_right</i></a>
                                            </td>
                                            <td>
                                                <img class="materialboxed" height="40" width="40" src="resources/img/Products/<%= buyProduct.getProduct().getImage() %>">
                                            </td>
                                            <td><%= String.format("R$ %,.2f", buyProduct.getProduct().getPrice()).replace(",", ".") %></td>
                                            <td><%= String.format("R$ %,.2f", (buyProduct.getTotalPrice())).replace(",", ".") %></td>
                                        </tr>
                                    <% } %>
                                    </tbody>
                                    <div class="divider"></div>
                                    <tr>
                                        <td colspan="6">TOTAL</td>
                                        <td><%= String.format("R$ %,.2f", (buyTotalPrice)).replace(",", ".") %></td>
                                    </tr>
                                </table><br><br>
                                    <div class="row">
                                        <a href="buy?action=complete-purchase" class="waves-effect waves-light btn green lighten-1 col s12 m3 right">
                                            <i class="material-icons right">shopping_cart</i>
                                            Concluir Compra
                                        </a>
                                    </div>
                                <% } %>
                            <% } else { %>
                            <p>
                                <i class="material-icons black-text">sentiment_very_dissatisfied</i>
                                Ahh não, seu carrinho está vazio...
                            </p>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>