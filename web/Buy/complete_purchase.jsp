<%-- 
    Document   : complete_purchase
    Created on : 16/06/2018, 11:18:24
    Author     : jefferson
--%>

<%@page import="Model.BuyProduct"%>
<%@page import="Model.Client"%>
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
                        <form method="post" action="buy?action=checkout">
                            <% Client client = (Client) request.getSession().getAttribute("client"); %>
                            <div class="row">
                                <div class="col s12 m6">
                                    <div class="row">
                                        <span class="card-title black-text">ENDEREÇO DE ENTREGA</span>    
                                        <p>
                                            <b>Endereço: </b> <%= client.getAddress() + ", " + client.getHomeNumber() %>
                                        </p>
                                        <p>
                                            <b>Bairro: </b> <%= client.getDistrict() %>
                                        </p>
                                        <p>
                                            <b>Cidade: </b> <%= client.getCity().getName() + ", " + client.getCity().getState().getUf() %>
                                        </p>
                                        <p>
                                            <b>CEP: </b> <%= client.getZipcode() %>
                                        </p>
                                    </div>
                                    <div class="row">
                                        <p>
                                          <label>
                                            <input class="filled-in" id="use_another" name="option" value="use_another" type="checkbox" />
                                            <span>Quer receber seus produtos em outro endereço?</span>
                                          </label>
                                        </p>
                                    </div>
                                    <div class="row" id="another_address">
                                        <div class="preloader-wrapper small">
                                            <div class="spinner-layer spinner-green-only">
                                                <div class="circle-clipper left">
                                                    <div class="circle"></div>
                                                </div><div class="gap-patch">
                                                    <div class="circle"></div>
                                                </div><div class="circle-clipper right">
                                                    <div class="circle"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col s12 m6">
                                    <table class="highlight">
                                        <thead>
                                            <tr>
                                                <th>Item</th>
                                                <th>Qtde</th>
                                                <th>Valor Unitário</th>
                                                <th>Valor Total</th>
                                            </tr>
                                        </thead>
                                        <% double buyTotalPrice = 0; %>
                                        <tbody>
                                        <% List<BuyProduct> cart = (List<BuyProduct>) request.getSession().getAttribute("cart"); %>
                                        <% Iterator<BuyProduct> iterator = cart.iterator(); %>
                                        <% while (iterator.hasNext()) { %>
                                            <% BuyProduct buyProduct = iterator.next(); %>
                                            <% buyTotalPrice += buyProduct.getTotalPrice(); %>
                                            <tr>
                                                <td><%= buyProduct.getProduct().getName() %></td>
                                                <td>
                                                    <%= buyProduct.getAmount() %>
                                                </td>
                                                <td><%= String.format("R$ %,.2f", buyProduct.getProduct().getPrice()).replace(",", ".") %></td>
                                                <td><%= String.format("R$ %,.2f", (buyProduct.getTotalPrice())).replace(",", ".") %></td>
                                            </tr>
                                        <% } %>
                                        </tbody>
                                        <div class="divider"></div>
                                        <tr class="grey lighten-4">
                                            <td colspan="3">TOTAL</td>
                                            <td><%= String.format("R$ %,.2f", (buyTotalPrice)).replace(",", ".") %></td>
                                        </tr>
                                    </table><br>
                                    <div class="row">
                                        <button class="btn waves-effect waves-light right black col s12 m6 l4" type="submit" name="action">Finalizar</button>   
                                    </div>
                                </div>
                            </div>
                        </form>
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
    
   
    
    $("#use_another").change(function() {
        if ($("#use_another").is(":checked")) {
            $.ajax({
                url: 'buy?action=load-address-form',
                beforeSend: function () {
                    $(".preloader-wrapper").addClass("active");
                }
            })
                .done(function (data) {
                    $(".preloader-wrapper").removeClass("active");
                    $("#another_address").html(data);
                });
        } else {
            $(".preloader-wrapper").removeClass("active");
            $("#another_address").html("");
        }
        
    });
</script>