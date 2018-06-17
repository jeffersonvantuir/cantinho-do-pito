<%-- 
    Document   : timeout
    Created on : 17/06/2018, 00:06:35
    Author     : ricardo
--%>

<%@page import="Controller.AccessController"%>
<%
    request.getSession().getAttribute("client");

    AccessController accessController = new AccessController();
    accessController.timeOut(request, response);
%>