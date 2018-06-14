<%-- 
    Document   : footer
    Created on : 09/06/2018, 22:27:23
    Author     : ricardo
--%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<footer class="page-footer black">
    <div class="container">
        <div class="row">
            <div class="col l7 s12">
                <div class="col l2 s12">
                    <a href="" class="brand-logo"><img class="circle logo" src="/cantinho-do-pito/resources/img/logo.png"></a>
                </div>
                <div class="col l10 s12">

                    <h5 class="white-text">Cantinho do Pito</h5>
                    <p class="grey-text text-lighten-4">O Cantinho do Pito é uma loja ficticia especializada na venda de produtos utilizados aos finais de semana.</p>
                </div>
            </div>
            <div class="col l4 offset-l1 s12">
                <h5 class="white-text">Contatos</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!"><i class="material-icons tiny">mail</i> cantinho_do_pito@fumaca.com</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!"><i class="material-icons tiny">phone</i> +99 99 999999999</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            <% 
            Calendar cal = GregorianCalendar.getInstance();
            %>
            © 2018 - <%= cal.get(Calendar.YEAR) %> Jefferson and Ricardo.
            <a class="grey-text text-lighten-4 right" href="https://github.com/" target="_black">GitHub</a>
        </div>
    </div>
</footer>  
