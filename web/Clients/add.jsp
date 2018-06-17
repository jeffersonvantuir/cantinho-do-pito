<%-- 
    Document   : add
    Created on : 07/06/2018, 22:00:00
    Author     : jefferson
--%>

<%@page import="Controller.AccessController"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.State"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Novo Cliente</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <%--<jsp:include page="../Layout/navbar.jsp"/>--%>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <div class="col s12 m6 offset-m3 l6 offset-l3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Novo cadastro</span>
                            <form method="post" action="clients?action=add">
                                <div class="row">
                                    <h6>
                                        <i class="material-icons">person</i>
                                        Dados Pessoais
                                    </h6>
                                    <div class="divider"></div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input id="name" name="name" type="text" required>
                                        <label for="name">Nome</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">email</i>
                                        <input id="email" name="email" type="email" class="validate"  required>
                                        <label for="email">E-mail</label>
                                        <span class="helper-text" data-error="E-mail inválido">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">chrome_reader_mode</i>
                                        <input id="cpf" name="cpf" type="text" required>
                                        <label for="cpf">CPF</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">local_phone</i>
                                        <input id="cellphone" name="cellphone" type="text" required>
                                        <label for="cellphone">Telefone</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">date_range</i>
                                        <label for="birthday">Data de Nascimento</label>
                                        <input id="birthday" name="birthday" type="text" class="datepicker">
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">lock</i>
                                        <input id="password" name="password" type="password" required>
                                        <label for="password">Senha</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">lock</i>
                                        <input id="confirm_password" name="confirm_password" type="password" required>
                                        <label for="confirm_password">Confirmar Senha</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <h6 class="col s12">
                                        <i class="material-icons">location_city</i>
                                        Endereço
                                    </h6>
                                    <div class="divider col s12"></div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="zipcode" name="zipcode" type="text" required>
                                        <label for="zipcode">CEP</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="address" name="address" type="text" required>
                                        <label for="address">Endereço</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="number" name="home_number" type="number">
                                        <label for="number">Número</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="complement" name="complement" type="text">
                                        <label for="complement">Complemento</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="district" name="district" type="text">
                                        <label for="district">Bairro</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <select name="state" id="state" required>
                                            <%
                                                if (request.getAttribute("listStates") != null) {
                                                    List<State> listStates = (List<State>) request.getAttribute("listStates");
                                                    Iterator i = listStates.iterator();
                                                    while (i.hasNext()) {
                                                        State state = (State) i.next();
                                            %>
                                            <option value="<%= state.getId()%>"><%= state.getName()%></option>
                                            <%
                                                    }
                                                } else {
                                                    AccessController accessController = new AccessController();
                                                    accessController.directoryControl(request, response);
                                                }
                                            %>
                                        </select>
                                        <label>Estado</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <select name="city" id="city" required>
                                        </select>
                                        <label>Cidade</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <button class="btn waves-effect waves-light right black col s12 m6 offset-m5 l4 offset-l7" type="submit" name="action">Cadastrar
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
<script>

    $('#city').on('contentChanged', function () {
        $(this).formSelect();
    });

    $(function () {
        $('#state').change(function () {
            $.ajax({
                url: 'clients?action=load-cities&state_id=' + this.value,
                beforeSend: function () {
                    $("#city").trigger('contentChanged');
                }
            })
                    .done(function (data) {
                        $("#city").html(data);
                        $("#city").trigger('contentChanged');
                    })
        })
    });
    $(document).ready(function () {
        $.ajax({
            url: 'clients?action=load-cities&state_id=' + $("#state").val(),
            beforeSend: function () {
                $("#city").trigger('contentChanged');
            }
        })
                .done(function (data) {
                    $("#city").append(data);
                    $("#city").trigger('contentChanged');
                })
    });
</script>