<%-- 
    Document   : buy_requests
    Created on : 21/06/2018, 21:35:41
    Author     : ricardo
--%>
<%@page import="Helpers.Helper"%>
<%@page import="Model.Buy"%>
<%@page import="Controller.AccessController"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista de Compras</title>
        <jsp:include page="../Layout/default.jsp"/>
        <jsp:include page="../Layout/timeout.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m10 offset-m1 l10 offset-l1">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Lista de Compras</span>
                            <table class="striped responsive-table">
                                <thead>
                                    <tr>
                                        <th>Nome do Cliente</th>
                                        <th>CPF</th>
                                        <th>E-mail</th>
                                        <th>Celular</th>
                                        <th>Preço Total</th>
                                        <th>Data da Compra</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (request.getAttribute("listBuyRequests") != null) {
                                            List<Buy> listBuyRequests = (List<Buy>) request.getAttribute("listBuyRequests");
                                            Iterator i = listBuyRequests.iterator();
                                            Helper helper = new Helper();

                                            while (i.hasNext()) {
                                                Buy buy = (Buy) i.next();
                                    %>
                                    <tr>
                                        <td><%= buy.getClient().getName()%></td>
                                        <td><%= buy.getClient().getCpf()%></td>
                                        <td><%= buy.getClient().getEmail()%></td>
                                        <td><%= buy.getClient().getCellphone()%></td>
                                        <td><%= String.format("R$ %,.2f", buy.getTotalPrice()).replace(",", ".")%></td>
                                        <td><%= helper.formatterDateUsage(buy.getDate())%></td>
                                        <td>
                                            <a href="buy?action=accept-purchase&id=<%= buy.getId()%>">
                                                <i class="material-icons" title="Aceitar Compra">assignment_turned_in</i>
                                            </a>
                                        </td>
                                    </tr>
                                    <% }
                                        } else {
                                            AccessController accessController = new AccessController();
                                            accessController.directoryControl(request, response);
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>