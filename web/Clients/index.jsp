<%-- 
    Document   : index
    Created on : 10/06/2018, 10:06:12
    Author     : jefferson
--%>
<%@page import="Controller.AccessController"%>
<%@ page import="Model.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Clientes</title>
        <jsp:include page="../Layout/default.jsp"/>
        <jsp:include page="../Layout/timeout.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m6 offset-s3 l10 offset-l1">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Clientes</span>
                            <table class="striped responsive-table">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>E-mail</th>
                                        <th>CPF</th>
                                        <th>Telefone</th>
                                        <th>Endereço</th>
                                        <th>Cidade - UF</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        if (request.getAttribute("listClients") != null) {
                                            List<Client> listClients = (List<Client>) request.getAttribute("listClients");
                                            Iterator i = listClients.iterator();
                                            while (i.hasNext()) {
                                                Client client = (Client) i.next();
                                    %>
                                    <tr>
                                        <td><%= client.getName()%></td>
                                        <td><%= client.getEmail()%></td>
                                        <td><%= client.getCpf()%></td>
                                        <td><%= client.getCellphone()%></td>
                                        <td><%= client.getAddress()%></td>
                                        <td><%= client.getCity().getName() + ", " + client.getCity().getState().getUf()%></td>
                                        <td>
                                            <a href="clients?action=request-edit&id=<%= client.getId()%>">
                                                <i class="material-icons" title="Editar">edit</i>
                                            </a>&nbsp
                                            <a href="clients?action=delete&id=<%= client.getId()%>"  onclick="return confirm('Deseja excluir o cliente <%= client.getName()%>?')">
                                                <i class="material-icons" title="Excluir">delete</i>
                                            </a>&nbsp
                                        </td>
                                    </tr>
                                    <% }
                                        } else {
                                            AccessController accessController = new AccessController();
                                            accessController.directoryControl(request, response);
                                        }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>