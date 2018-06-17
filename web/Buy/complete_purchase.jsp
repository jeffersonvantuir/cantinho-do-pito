<%-- 
    Document   : complete_purchase
    Created on : 16/06/2018, 11:18:24
    Author     : jefferson
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Model.State"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cantinho do Pito</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar_client.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="card darken-1 col s12 m10 offset-m1">
                    <div class="card-content black-text">
                        
                        <div class="row">
                            <span class="card-title black-text">ENDEREÇO DE ENTREGA</span>    
                            <h6>Endereço</h6>
                            
                            <p>
                              <label>
                                <input class="with-gap" id="use_another" name="option" value="use_another" type="radio" />
                                <span>Utilizar outro endereço</span>
                              </label>
                            </p>
                        </div>
                        <div class="row hide" id="another_address">
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
                                        List<State> listStates = (List<State>) request.getAttribute("listStates");
                                        Iterator i = listStates.iterator();
                                        while (i.hasNext()) {
                                            State state = (State) i.next();
                                    %>
                                    <option value="<%= state.getId()%>"><%= state.getName()%></option>
                                    <% }%>
                                </select>
                                <label>Estado</label>
                            </div>
                            <div class="input-field col s12 m6 l6">
                                <select name="city" id="city" required>
                                </select>
                                <label>Cidade</label>
                            </div>
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
        });
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
    
    $("#use_another").change(function() {
        $("#another_address").removeClass("hide");
    });
    
    $("#use_the_same").change(function() {
        $("#another_address").addClass("hide");
    });
    
</script>