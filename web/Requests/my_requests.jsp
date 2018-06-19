<%-- 
    Document   : my_requests
    Created on : 18/06/2018, 19:07:11
    Author     : jefferson
--%>
<%@page import="Model.Request"%>
<%@page import="Model.BuyProduct"%>
<%@page import="Model.Buy"%>
<%@page import="Helpers.Helper"%>
<%@page import="Model.Category"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
                            <span class="card-title black-text">Suas compras</span>
                            <% if (request.getAttribute("listBuy") != null) { %>
                                <% List<Buy> listBuy = (List<Buy>) request.getAttribute("listBuy"); %>
                                <% if (listBuy.isEmpty()) { %>
                                <p>
                                    <i class="material-icons black-text">sentiment_very_dissatisfied</i>
                                    Ahh não, você não fez nenhuma compra ainda...
                                </p>
                                <% } else { %>
                                <table class="highlight">
                                    <thead>
                                        <tr>
                                            <th>Data</th>
                                            <th>Valor Total</th>
                                            <th>Status</th>
                                            <th>Ações</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% 
                                    Iterator<Buy> iter = listBuy.iterator();
                                    Helper helper = new Helper();
                                    
                                    while (iter.hasNext()) {
                                        Buy buy = iter.next();
                                    %>
                                        <tr>
                                            <td><%= helper.formatterDateUsage(buy.getDate()) %></td>
                                            <td><%= String.format("R$ %,.2f", buy.getTotalPrice()).replace(",", ".") %></td>
                                            <td>
                                                <% if (buy.isAuthorized()) { %>
                                                <span class="badge green black-text">Autorizado</span>
                                                <% } else { %>
                                                <span class="badge yellow black-text">Aguardando</span>
                                                <% } %>
                                            </td>
                                            <td>
                                                <button data-target="<%= buy.getId() %>" class="btn modal-trigger orange">Ver Produtos</button>
                                            </td>
                                        </tr>
                                        <div id="<%= buy.getId() %>" class="modal">
                                            <div class="modal-content">
                                                <ul class="collection with-header">
                                                    <li class="collection-header"><h5>Produtos</h5></li>
                                                    <% List<BuyProduct> listBuyProduct = buy.getListBuyProduct(); %>
                                                    <% Iterator<BuyProduct> iterator = listBuyProduct.iterator(); %>
                                                    <% while(iterator.hasNext()) { %>
                                                        <% BuyProduct buyProduct = iterator.next(); %>
                                                        <li class="collection-item">
                                                            <div>
                                                                <%= buyProduct.getProduct().getName() %>
                                                                <div class="secondary-content"><%= String.format("R$ %,.2f", buyProduct.getProduct().getPrice()).replace(",", ".") %></div>
                                                            </div>
                                                        </li>
                                                    <% } %>
                                                </ul>
                                            </div>
                                            <div class="modal-footer">
                                                <a href="#!" class="modal-close waves-effect waves-green btn-flat">Fechar</a>
                                            </div>
                                        </div>
                                    <% } %>
                                    </tbody>
                                </table><br><br>
                                <% } %>
                            <% } %>
                            <br><br>
                            <span class="card-title black-text">Suas solicitações</span>
                            <% if (request.getAttribute("listRequests") != null) { %>
                                <% List<Request> listRequests = (List<Request>) request.getAttribute("listRequests"); %>
                                <% if (listRequests.isEmpty()) { %>
                                <p>
                                    <i class="material-icons black-text">sentiment_very_dissatisfied</i>
                                    Ahh não, você ainda não possui nenhuma solicitação...
                                </p>
                                <% } else { %>
                                <table class="highlight">
                                    <thead>
                                        <tr>
                                            <th>Item</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% 
                                    Iterator<Request> iter = listRequests.iterator();
                                    Helper helper = new Helper();
                                    
                                    while (iter.hasNext()) {
                                        Request r = iter.next();
                                    %>
                                        <tr>
                                            <td><%= r.getProduct().getName() %></td>
                                            <td>
                                                <% if (r.isStatus()) { %>
                                                <a href="home?action=view&id=<%= r.getProduct().getId() %>">
                                                    <span class="badge green white-text">Disponível, clique aqui</span>
                                                </a>
                                                <% } else { %>
                                                <span class="badge red white-text">Em falta</span>
                                                <% } %>
                                            </td>
                                        </tr>
                                    <% } %>
                                    </tbody>
                                </table><br><br>
                                <% } %>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>

<script>
    $(document).ready(function(){
        $('.modal').modal();
    });
</script>