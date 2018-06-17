<%-- 
    Document   : index
    Created on : 13/06/2018, 21:39:41
    Author     : ricardo
--%>
<%@page import="Controller.AccessController"%>
<%@ page import="Model.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Marcas</title>
        <jsp:include page="../Layout/default.jsp"/>
        <jsp:include page="../Layout/timeout.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m6 offset-s3 l6 offset-l3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Marcas</span>
                            <table class="striped responsive-table">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (request.getAttribute("listBrands") != null) {
                                            List<Brand> listBrands = (List<Brand>) request.getAttribute("listBrands");
                                            Iterator i = listBrands.iterator();
                                            while (i.hasNext()) {
                                                Brand brand = (Brand) i.next();
                                    %>
                                    <tr>
                                        <td><%= brand.getName()%></td>
                                        <td>
                                            <a href="brands?action=request-edit&id=<%= brand.getId()%>">
                                                <i class="material-icons" title="Excluir">edit</i>
                                            </a>&nbsp
                                            <a href="brands?action=delete&id=<%= brand.getId()%>"  onclick="return confirm('Deseja excluir a marca <%= brand.getName()%>?')">
                                                <i class="material-icons" title="Excluir">delete</i>
                                            </a>&nbsp
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
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>