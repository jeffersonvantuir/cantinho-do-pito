<%-- 
    Document   : add
    Created on : 09/06/2018, 15:20:50
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
        <title>Adicionar Categoria</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <div class="row">
            <div class="col s12 m6 offset-m3 l6 offset-l3">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">Nova Categoria</span>
                        <form method="post" action="categories?action=add">
                            <div class="row">
                                <h6>
                                    <i class="material-icons">category</i>
                                    Dados da Categoria
                                </h6>
                                <div class="divider"></div>
                                <div class="input-field col s12 m12 l12">
                                    <i class="material-icons prefix">assignment_turned_in</i>
                                    <input id="name" name="name" type="text">
                                    <label for="name">Nome</label>
                                </div>
                                <div class="input-field col s12 m12 l12">
                                    <i class="material-icons prefix">descriptiont</i>
                                    <textarea id="icon_prefix2" class="materialize-textarea" name="description"></textarea>
                                    <label for="icon_prefix2">Descrição</label>
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
