<%-- 
    Document   : add
    Created on : 07/06/2018, 22:00:00
    Author     : jefferson
--%>

<%@page import="Controller.AccessController"%>
<%@page import="Model.Client"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.State"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%
    if (request.getAttribute("client") != null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar Cliente</title>
        <jsp:include page="../Layout/default.jsp"/>
        <jsp:include page="../Layout/timeout.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <div class="col s12 m6 offset-m3 l6 offset-l3">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Editar cliente</span>
                            <form method="post" action="clients?action=edit">
                                <%                                    Client client = (Client) request.getAttribute("client");
                                    session.setAttribute("id", client.getId());
                                %>
                                <div class="row">
                                    <h6>
                                        <i class="material-icons">person</i>
                                        Dados Pessoais
                                    </h6>
                                    <div class="divider"></div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input id="name" name="name" required type="text" value="<%= client.getName()%>">
                                        <label for="name">Nome</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">email</i>
                                        <input id="email" name="email" type="email" class="validate" required value="<%= client.getEmail()%>">
                                        <label for="email">E-mail</label>
                                        <span class="helper-text" data-error="E-mail inválido">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">chrome_reader_mode</i>
                                        <input id="cpf" name="cpf" type="text" required value="<%= client.getCpf()%>">
                                        <label for="cpf">CPF</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">local_phone</i>
                                        <input id="cellphone" name="cellphone" type="text" required value="<%= client.getCellphone()%>">
                                        <label for="cellphone">Telefone</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">date_range</i>
                                        <label for="birthday">Data de Nascimento</label>
                                        <input id="birthday" name="birthday" type="text" class="datepicker" value="<%= client.getBirthday()%>">
                                    </div>
                                </div>
                                <div class="row">
                                    <h6 class="col s12">
                                        <i class="material-icons">location_city</i>
                                        Endereço
                                    </h6>
                                    <div class="divider col s12"></div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="zipcode" name="zipcode" type="text" required value="<%= client.getZipcode()%>">
                                        <label for="zipcode">CEP</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="address" name="address" type="text" required value="<%= client.getAddress()%>">
                                        <label for="address">Endereço</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="number" name="home_number" type="number" value="<%= client.getHomeNumber()%>">
                                        <label for="number">Número</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="complement" name="complement" type="text" value="<%= client.getComplement()%>">
                                        <label for="complement">Complemento</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <input id="district" name="district" type="text" value="<%= client.getDistrict()%>">
                                        <label for="district">Bairro</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <select name="state" id="state">
                                            <%
                                                List<State> listStates = (List<State>) request.getAttribute("listStates");
                                                Iterator i = listStates.iterator();
                                                State stateSelect = (State) request.getAttribute("stateSelect");

                                                while (i.hasNext()) {
                                                    State state = (State) i.next();

                                                    if (stateSelect.getId() == state.getId()) {
                                            %>
                                            <option value="<%= state.getId()%>" selected><%= state.getName()%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value="<%= state.getId()%>"><%= state.getName()%></option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>
                                        <label>Estado</label>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <select name="city" id="city">
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
    </body>
</main>
<jsp:include page="../Layout/footer.jsp"/>
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

                    var citySelectedId = "<%= client.getCityId()%>";
                    var cityselect = document.getElementById('city');

                    for (var i, j = 0; i = cityselect.options[j]; j++) {
                        if (i.value == citySelectedId) {
                            cityselect.selectedIndex = j;
                            $("#city").trigger('contentChanged');
                            break;
                        }
                    }
                });
    });
</script>
<%
    } else {
        AccessController accessController = new AccessController();
        accessController.directoryControl(request, response);
    }
%>