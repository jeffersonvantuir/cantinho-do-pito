<%@page import="Model.State"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.City"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%
List<City> listStates = (List<City>) request.getAttribute("listCities");
Iterator i = listStates.iterator();

while (i.hasNext()) {
    City state = (City) i.next();
%>
<option value="<%= state.getId() %>"><%= state.getName() %></option>
<% } %>