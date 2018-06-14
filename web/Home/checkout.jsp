<%-- 
    Document   : checkout
    Created on : 13/06/2018, 23:03:15
    Author     : jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cantinho do Pito</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <jsp:include page="../Layout/flash.jsp"/>
                <div class="col s12 m10 offset-m1">
                  <div class="card darken-1">
                    <div class="card-content black-text">
                      <span class="card-title black-text"></span>

                    </div>
                  </div>
                </div>
            </div>
        </main>
        <jsp:include page="../Layout/footer.jsp"/>
    </body>
</html>