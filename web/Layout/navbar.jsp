<%--
    Document   : navbar
    Created on : 09/06/2018, 21:12:58
    Author     : ricardo
--%>
<%@page import="Model.Client"%>
<%@page import="Controller.AccessController"%>
<%
    request.getSession().getAttribute("client");

    AccessController accessController = new AccessController();
    accessController.accessControl(request, response);
%>
<header>
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper black">
                <ul id="nav-mobile" class="left hide-on-med-and-down">
                    <li><a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a></li>
                    <li><a href="dashboard">Dashboard</a></li>
                    <li><a href="categories">Categorias</a></li>
                    <li><a href="clients">Clientes</a></li>
                    <li><a href="products">Produtos</a></li>
                    <li><a href="brands">Marcas</a></li>
                </ul>
                <ul>
                    <% Client client = (Client) request.getSession().getAttribute("client");%>
                    <a class='dropdown-trigger right' href='#' data-target='actions'><%= client.getName()%></a>
                    <ul id='actions' class='dropdown-content'>
                        <li><a href="dashboard" class="black-text"><i class="material-icons">dashboard</i>Dashboard</a></li>
                        <li><a href="requests?action=my-requests" class="black-text"><i class="material-icons">view_module</i>Meus pedidos</a></li>
                        <li><a href="access?action=logout" class="black-text"><i class="material-icons">exit_to_app</i>Sair</a></li>
                    </ul>
                </ul>
            </div>
        </nav>
    </div>
</header>