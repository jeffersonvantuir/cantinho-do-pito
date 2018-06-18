<%@page import="java.util.Iterator"%>
<%@page import="Model.State"%>
<%@page import="java.util.List"%>
<jsp:include page="../Layout/default.jsp"/>
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