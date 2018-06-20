<%
    String success = String.valueOf(request.getAttribute("success"));
    String error = String.valueOf(request.getAttribute("error"));
    if (!success.equals("null")) {
%>
<div class="col s12 m12 l12 blue" id="flash">
    <p class="center"><%= success %></p>
</div>
<script>
    $("#flash").click(function () {
        $("#flash").addClass("hiddendiv")
    });
</script>
<%
} else if (!error.equals("null")) {
%>
<div class="col s12 m12 l12 red" id="flash">
    <p class="center"><%= error %></p>
</div>
<script>
    $("#flash").click(function () {
        $("#flash").addClass("hiddendiv")
    });
</script>
<%
    }
%>
