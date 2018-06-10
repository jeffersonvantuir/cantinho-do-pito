<%-- 
    Document   : sidenav
    Created on : 09/06/2018, 22:15:53
    Author     : ricardo
--%>

<ul id="categories" class="dropdown-content">
    <li><a href="categories" class="waves-effect"><i class="material-icons">list</i>Listar Categorias</a></li>
    <li><a href="categories?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Categoria</a></li>
</ul>

<ul id="slide-out" class="sidenav">
    <li><div class="user-view">
            <div class="background">
                <img src="/cantinho-do-pito/resources/img/fundo.jpg">
            </div>
            <h5 class="white-text center">Cantinho do Pito</h5>
            <br>
        </div></li>
    <li><a><i class="material-icons">send</i>Ações</a></li>
    <li><div class="divider"></div></li>
    <li>
        <a class="dropdown-trigger" href="#!" data-target="categories">Categorias<i class="material-icons right">arrow_drop_down</i></a>
    </li>
</ul>
<script>
    $(".dropdown-trigger").dropdown();
</script>