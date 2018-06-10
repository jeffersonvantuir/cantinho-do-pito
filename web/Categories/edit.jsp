<%-- 
    Document   : edit
    Created on : 09/06/2018, 19:30:08
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
        <title>Editar Categoria</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <div class="row">
            <jsp:include page="../Layout/navbar.jsp"/>
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
                    <a href="categories" class="waves-effect"><i class="material-icons">list</i>Listar Categorias</a>
                </li>
                <li>
                    <a href="categories?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Categoria</a>
                </li>
            </ul>
            <div class="col s12 m6 offset-m3 l6 offset-l3">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">Editar Categoria</span>
                        <form method="post" action="categories?action=edit">
                            <%
                                Category category = (Category) request.getAttribute("categoryEdit");
                                session.setAttribute("id", category.getId());
                            %>
                            <div class="row">
                                <h6>
                                    <i class="material-icons">category</i>
                                    Dados da Categoria
                                </h6>
                                <div class="divider"></div>
                                <div class="input-field col s12 m12 l12">
                                    <i class="material-icons prefix">assignment_turned_in</i>
                                    <input id="name" name="name" type="text" value="<%=category.getName()%>">
                                    <label for="name">Nome</label>
                                    <span class="helper-text">* Campo Obrigatório</span
                                </div>
                                <div class="input-field col s12 m12 l12">
                                    <i class="material-icons prefix">descriptiont</i>
                                    <textarea id="icon_prefix2" class="materialize-textarea" name="description"><%=category.getDescription()%></textarea>
                                    <label for="icon_prefix2">Descrição</label>
                                    <span class="helper-text">* Campo Obrigatório</span
                                </div>
                                <button class="btn waves-effect waves-light right" type="submit" name="action">Enviar
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
