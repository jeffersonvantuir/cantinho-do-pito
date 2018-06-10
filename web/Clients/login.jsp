<%-- 
    Document   : login
    Created on : 10/06/2018, 15:36:15
    Author     : jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrar</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <main>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m6 offset-m3 l4 offset-l4">
                    <div class="card login">
                        <div class="card-content">
                            <span class="card-title"></span>
                            <form method="post" action="clients?action=login">
                                <div class="row">
                                    <h6>
                                        <i class="material-icons">person</i>
                                        Faça login
                                    </h6>
                                    <div class="divider"></div>
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">email</i>
                                        <input id="email" name="email" type="email" class="validate" required>
                                        <label for="email">E-mail</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">lock</i>
                                        <input id="password" name="password" type="password" required>
                                        <label for="password">Senha</label>
                                    </div>
                                    <a href="clients?action=request-add">Cadastre-se</a><br>
                                    <a href="clients?action=forgot-password">Esqueceu a senha?</a><br>
                                </div>
                                <div class="row">
                                    <button class="btn waves-effect waves-light col s12 m6 offset-m5 l4 offset-l4" type="submit" name="action">Entrar
                                        <i class="material-icons right">send</i>
                                    </button>    
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
