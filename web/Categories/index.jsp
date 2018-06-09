<%-- 
    Document   : index
    Created on : 09/06/2018, 14:20:32
    Author     : ricardo
--%>
<%@ page import="Model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <div class="row">
            <div class="col s12 m6 m6">
                <div class="card blue-grey darken-1">
                    <div class="card-content white-text">
                        <table class="striped">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Descrição</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Category> listaDeClientes = (List<Category>) request.getAttribute("indexCategories");
                                    Iterator i = listaDeClientes.iterator();
                                    while (i.hasNext()) {
                                        Category category = (Category) i.next();
                                %>
                                <tr>
                                    <td><%= category.getId()%></td>
                                    <td><%= category.getName()%></td>
                                    <td>
                                        <a href="">Editar</a>
                                        <a href="">Excluir</a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    </body>
</html>
