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
            <%
                String success = String.valueOf(request.getAttribute("success"));
                String error = String.valueOf(request.getAttribute("error"));
                if (!success.equals("null")) {
            %>
            <div class="col s12 m12 l12 blue" id="flash">
                <p class="center"><%= success%></p>
            </div>
            <%
            } else if (!error.equals("null")) {
            %>
            <div class="col s12 m12 l12 red" id="flash">
                <p class="center"><%= error%></p>
            </div>
            <%
                }
            %>
            <div class="col s12 m6 offset-m3 l6 offset-l3">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">Categorias</span>
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
                                    <td><%= category.getName()%></td>
                                    <td><%= category.getDescription()%></td>
                                    <td>
                                        <a href="categories?action=request-edit&id=<%= category.getId()%>">Editar</a>&nbsp&nbsp
                                        <a href="categories?action=delete&id=<%= category.getId()%>"  onclick="return confirm('Deseja excluir a categoria <%= category.getName()%>?')">Excluir</a>&nbsp&nbsp
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
<script>
    $("#flash").click(function () {
        $("#flash").addClass("hiddendiv")
    });
</script>