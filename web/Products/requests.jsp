<%-- 
    Document   : requests
    Created on : 17/06/2018, 14:22:11
    Author     : ricardo
--%>
<%@page import="Controller.AccessController"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
        <jsp:include page="../Layout/default.jsp"/>
        <jsp:include page="../Layout/timeout.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m12 l10 offset-l1">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Solicitações de Produtos</span>
                            <table class="striped responsive-table">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Descrição</th>
                                        <th>Preço</th>
                                        <th>Estoque</th>
                                        <th>Marca</th>
                                        <th>Categoria</th>
                                        <th>Imagem</th>
                                        <th>Pedidos</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (request.getAttribute("listRequests") != null) {
                                            List<Product> listRequests = (List<Product>) request.getAttribute("listRequests");
                                            Iterator i = listRequests.iterator();
                                            while (i.hasNext()) {
                                                Product product = (Product) i.next();
                                    %>
                                    <tr>
                                        <td><%= product.getName()%></td>
                                        <td><%= product.getDescription()%></td>
                                        <td><%= String.format("R$ %,.2f", product.getPrice()).replace(",", ".")%></td>
                                        <td><%= product.getStock()%></td>
                                        <td><%= product.getBrand().getName()%></td>
                                        <td><%= product.getCategory().getName()%></td>
                                        <td>
                                            <img class="materialboxed" height="40" width="40" src="resources/img/Products/<%= product.getImage()%>">
                                        <td><%= product.getRequests()%></td>
                                        <td>
                                            <a href="products?action=request-edit&id=<%= product.getId()%>" title="Atualizar estoque">
                                                <i class="material-icons">create</i>
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                            }
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
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>
