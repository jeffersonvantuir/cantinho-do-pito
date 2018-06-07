<%@ page import="Model.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todos os Clientes</title>
</head>
<body>
   <h3>Clientes Cadastrados</h3>
    <table border="1">
        <thead>
            <th>ID</th>
            <th>Nome</th>
        </thead>
        <tbody>
        <%
            List<Client> listaDeClientes = (List<Client>) request.getAttribute("listClients");
            Iterator i = listaDeClientes.iterator();
            while (i.hasNext()) {
                Client client = (Client) i.next();
        %>
            <tr>
                <td><%= client.getId() %></td>
                <td><%= client.getName()%></td>
            </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>
