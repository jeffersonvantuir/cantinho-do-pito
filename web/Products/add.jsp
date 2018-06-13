<%-- 
    Document   : add
    Created on : 10/06/2018, 22:36:28
    Author     : jefferson
--%>

<%@page import="Model.Brand"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <title>Adicionar Produto</title>
        <jsp:include page="../Layout/default.jsp"/>
    </head>
    <body>
        <jsp:include page="../Layout/navbar.jsp"/>
        <main>
            <jsp:include page="../Layout/sidenav.jsp"/>
            <div class="row">
                <div class="col s12 m6 offset-m3 l8 offset-l2">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">Novo Produto</span>
                            <form method="post" action="products?action=add" enctype="multipart/form-data">
                                <div class="row">
                                    <h6>
                                        <i class="material-icons">smoking_rooms</i>
                                        Dados do Produto
                                    </h6>
                                    <div class="divider"></div>
                                    <br>
                                    <img class="materialboxed col s12 m6 l6" id="imagePreview" height="350" src="#">
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">assignment_turned_in</i>
                                        <input id="name" name="name" type="text" required/>
                                        <label for="name">Nome</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">attach_money</i>
                                        <input type="text" id="price" name="price" required/>
                                        <label for="price">Preço Unitário</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">assignment_turned_in</i>
                                        <input id="stock" name="stock" type="number" required/>
                                        <label for="stock">Estoque</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">local_offer</i>
                                        <select name="brand_id">
                                            <%
                                                List<Brand> listBrands = (List<Brand>) request.getAttribute("listBrands");
                                                Iterator i = listBrands.iterator();
                                                while (i.hasNext()) {
                                                    Brand brand = (Brand) i.next();
                                                
                                             %>
                                             <option value="<%= brand.getId() %>"><%= brand.getName()%></option>
                                             <%
                                                 }
                                             %>
                                        </select>
                                        <label>Marca</label>
                                    </div>
                                    <div class="file-field input-field col s12 m6 l6">
                                      <div class="btn black">
                                        <span>File</span>
                                        <input type="file" id="file" name="image">
                                      </div>
                                      <div class="file-path-wrapper">
                                        <input class="file-path validate" required type="text">
                                        <span class="helper-text">* Campo Obrigatório</span>
                                      </div>
                                    </div>
                                    <div class="input-field col s12 m6 l6">
                                        <i class="material-icons prefix">category</i>
                                        <select name="category_id">
                                            <%
                                                List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
                                                Iterator in = listCategories.iterator();
                                                while (in.hasNext()) {
                                                    Category category = (Category) in.next();
                                                
                                             %>
                                             <option value="<%= category.getId() %>"><%= category.getName()%></option>
                                             <%
                                                 }
                                             %>
                                        </select>
                                        <label>Categoria</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">description</i>
                                        <textarea id="description" class="materialize-textarea" name="description"></textarea>
                                        <label for="description">Descrição</label>
                                        <span class="helper-text">* Campo Obrigatório</span>
                                    </div>
                                    <br>
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
    $(function() {
        $('#price').on('input', function() {
          this.value = this.value
            .replace(/[^\d.]/g, '')             // numbers and decimals only
            .replace(/(\..*)\./g, '$1')         // decimal can't exist more than once
            .replace(/(\.[\d]{2})./g, '$1');    // not more than 4 digits after decimal
        });
        
        $('#stock').on('input', function() {
          this.value = this.value
            .replace(/[^\d.]/g, '')             // numbers and decimals only
        });
  });
  
  function previewImage(input) {
      if (input.files && input.files[0]) {
          var image = new FileReader();
          
          image.onload = function(e) {
              $("#imagePreview").attr('src', e.target.result);
          }
          image.readAsDataURL(input.files[0]);
      }
  }
  
  $("#file").change(function () {
      previewImage(this);
  })
  
</script>