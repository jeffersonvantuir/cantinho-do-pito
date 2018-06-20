<%--
    Document   : sidenav
    Created on : 09/06/2018, 22:15:53
    Author     : ricardo
--%>

<ul id="brands" class="dropdown-content">
    <li><a href="brands" class="waves-effect"><i class="material-icons">list</i>Listar Marcas</a></li>
    <li><a href="brands?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Marcas</a></li>
</ul>
<ul id="categories" class="dropdown-content">
    <li><a href="categories" class="waves-effect"><i class="material-icons">list</i>Listar Categorias</a></li>
    <li><a href="categories?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Categoria</a></li>
</ul>
<ul id="clients" class="dropdown-content">
    <li><a href="clients" class="waves-effect"><i class="material-icons">list</i>Listar Clientes</a></li>
    <li><a href="clients?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Clientes</a></li>
</ul>
<ul id="products" class="dropdown-content">
    <li><a href="products" class="waves-effect"><i class="material-icons">list</i>Listar Produtos</a></li>
    <li><a href="products?action=request-add" class="waves-effect"><i class="material-icons">add</i>Adicionar Produtos</a></li>
    <li><a href="products?action=requests" class="waves-effect"><i class="material-icons">assignment_turned_in</i>Solicitações de Produtos</a></li>
    <li><a href="products?action=-low-stock" class="waves-effect"><i class="material-icons">assignment_late</i>Estoques Baixos</a></li>
</ul>

<ul id="slide-out" class="sidenav">
    <li>
        <div class="user-view">
            <a href="dashboard" title="Dashboard">

                <div class="background">
                    <img src="/cantinho-do-pito/resources/img/fundo.jpg">
                </div>
                <img class="circle" src="/cantinho-do-pito/resources/img/logo.png">
                <h5 class="white-text">Cantinho do Pito</h5>
                <br>
            </a>
        </div>
    </li>
    <li><a><i class="material-icons">send</i>Ações</a></li>
    <li><div class="divider"></div></li>
    <li>
        <a class="dropdown-trigger" href="#!" data-target="categories">Categorias<i class="material-icons right">keyboard_arrow_right</i></a>
    </li>
    <li>
        <a class="dropdown-trigger" href="#!" data-target="clients">Clientes<i class="material-icons right">keyboard_arrow_right</i></a>
    </li>
    <li>
        <a class="dropdown-trigger" href="#!" data-target="products">Produtos<i class="material-icons right">keyboard_arrow_right</i></a>
    </li>
    <li>
        <a class="dropdown-trigger" href="#!" data-target="brands">Marcas<i class="material-icons right">keyboard_arrow_right</i></a>
    </li>
</ul>
<script>
    $(".dropdown-trigger").dropdown();
</script>