<%-- 
    Document   : edit
    Created on : 13/06/2018, 22:23:51
    Author     : ricardo
--%>

<%@ page import="Model.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar Marca</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <div class="row">
                <jsp:include page="../Layout/sidenav.jsp"/>
                <div class="col s12 m6 offset-m3 l6 offset-l3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Editar Categoria</span>
                            <form method="post" action="brands?action=edit">
                                <%
                                    Brand brand = (Brand) request.getAttribute("brandEdit");
                                    session.setAttribute("id", brand.getId());
                                %>
                                <div class="row">
                                    <h6>
                                        <i class="material-icons">category</i>
                                        Dados da Marca
                                    </h6>
                                    <div class="divider"></div>
                                    <div class="input-field col s12 m12 l12">
                                        <i class="material-icons prefix">assignment_turned_in</i>
                                        <input id="name" name="name" type="text" value="<%=brand.getName()%>" required>
                                        <label for="name">Nome</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <br>
                                    <button class="btn waves-effect waves-light right black" type="submit" name="action">Enviar
                                        <i class="material-icons right">send</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>
