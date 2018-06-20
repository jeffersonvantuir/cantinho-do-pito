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
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#sortable").sortable({
                    placeholder: "ui-state-highlight"
                });
                $("#sortable").disableSelection();
            });
        </script>
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
                                <ul id="sortable">
                                    <li class="ui-state-default col s12 m4">
                                        <a href="products?action=requests" title="Solicitações pendentes">
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
                                        </a>
                                    </li>
                                    <li class="ui-state-default col s12 m4">
                                        <a href="products?action=low-stock" title="Estoques abaixo da média">
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
                                        </a>
                                    </li>
                                    <li class="ui-state-default col s12 m4">
                                        <a href="products?action=low-stock" title="Valor total de vendas deste mês">
                                            <div class="card">
                                                <div class="card-image purple">
                                                    <br>
                                                    <h5 class="center white-text">Valor total de vendas deste mês</h5>
                                                    <br>
                                                </div>
                                                <div class="card-content grey lighten-3">
                                                    <h2 class="center black-text">R$ <%= request.getAttribute("lowStock")%></h2>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="ui-state-default col s12 m4">
                                        <a href="products?action=low-stock" title="Número de vendas deste mês">
                                            <div class="card">
                                                <div class="card-image cyan">
                                                    <br>
                                                    <h5 class="center white-text">Número de vendas deste mês</h5>
                                                    <br>
                                                </div>
                                                <div class="card-content grey lighten-3">
                                                    <h2 class="center black-text"><%= request.getAttribute("lowStock")%></h2>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            /<% } else {
                    AccessController accessController = new AccessController();
                    accessController.directoryControl(request, response);
                }
            %>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>