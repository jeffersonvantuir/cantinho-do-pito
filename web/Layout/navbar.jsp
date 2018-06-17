<%--
    Document   : navbar
    Created on : 09/06/2018, 21:12:58
    Author     : ricardo
--%>
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
                    <li><a href="categories">Categorias</a></li>
                    <li><a href="clients">Clientes</a></li>
                    <li><a href="products">Produtos</a></li>
                    <li><a href="brands">Marcas</a></li>
                </ul>
                <a href="access?action=logout" class="right" title="Logout"><i class="material-icons">exit_to_app</i></a>
            </div>
        </nav>
    </div>
</header>