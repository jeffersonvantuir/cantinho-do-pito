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
        <jsp:include page="../Layout/navbar.jsp"/>
        <div class="row">
            <jsp:include page="../Layout/flash.jsp"/>
            <ul id="slide-out" class="sidenav">
                <li><div class="user-view">
                        <div class="background">
                            <img src="/cantinho-do-pito/resources/img/fundo.jpg">
                        </div>
                        <h5 class="white-text">Cantinho do Pito</h5>
                        <h6 class="white-text">Categorias</h6>
                        <br>
                    </div></li>
                    <li><a><i class="material-icons">send</i>Ações</a></li>
                <li><div class="divider"></div></li>
                <li>
                    <a href="categories?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Categoria</a>
                </li>
            </ul>
            
            <div class="col s12 m6 offset-s3 l6 offset-l3">
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