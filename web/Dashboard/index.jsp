<%-- 
    Document   : index
    Created on : 17/06/2018, 20:20:37
    Author     : ricardo
--%>

<%@page import="Controller.AccessController"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Dashboard</title>
        <jsp:include page="../Layout/default.jsp"/>
        <jsp:include page="../Layout/timeout.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <%
                if (request.getAttribute("allRequests") != null || request.getAttribute("lowStock") != null) {
            %>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m10 offset-s1 l12">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Dashboard</span>
                            <div class="row">
                                <a href="products?action=requests" title="Solicitações pendentes">
                                    <div class="col s12 m4 l4">
                                        <div class="card">
                                            <div class="card-image blue">
                                                <br>
                                                <h5 class="center white-text">Solicitações pendentes</h5>
                                                <br>
                                            </div>
                                            <div class="card-content grey lighten-3">
                                                <h2 class="center black-text"><%= request.getAttribute("allRequests")%></h2>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <a href="products?action=low-stock" title="Estoques abaixo da média">
                                    <div class="col s12 m4 l4">

                                        <div class="card">
                                            <div class="card-image green">
                                                <br>
                                                <h5 class="center white-text">Estoques abaixo da média</h5>
                                                <br>
                                            </div>
                                            <div class="card-content grey lighten-3">
                                                <h2 class="center black-text"><%= request.getAttribute("lowStock")%></h2>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <a href="buy?action=list-buys" title="Aprovar Compras Pendentes">
                                    <div class="col s12 m4 l4">
                                        <div class="card">
                                            <div class="card-image purple">
                                                <br>
                                                <h5 class="center white-text">Aprovar Compras</h5>
                                                <br>
                                            </div>
                                            <div class="card-content grey lighten-3">
                                                <h2 class="center black-text"><%= request.getAttribute("totalPurchasesToAccept")%></h2>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <div class="col s12 m4 l4">
                                    <div class="card">
                                        <div class="card-image cyan">
                                            <br>
                                            <h5 class="center white-text">Número de vendas deste mês</h5>
                                            <br>
                                        </div>
                                        <div class="card-content grey lighten-3">
                                            <h2 class="center black-text"><%= request.getAttribute("totalMonthlyPurchases")%></h2>
                                        </div>
                                    </div>
                                </div>
                                <div class="col s12 m4 l4">
                                    <div class="card">
                                        <div class="card-image teal">
                                            <br>
                                            <h5 class="center white-text">Valor total de vendas deste mês</h5>
                                            <br>
                                        </div>
                                        <div class="card-content grey lighten-3">
                                            <h2 class="center black-text"><%= String.format("R$ %,.2f", request.getAttribute("totalMonthlyAmount")).replace(",", ".")%></h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% } else {
                    AccessController accessController = new AccessController();
                    accessController.directoryControl(request, response);
                }
            %>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>