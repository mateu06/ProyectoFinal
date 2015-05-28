<#include "base.ftl">

<#macro content>


<form class="form-group" name="book" action="/book/edit/${libro.id}" method ="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Título</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="titulo" value="${libro.titulo}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Autor</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="autor" value="${libro.autor}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Precio</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="precio" value="${libro.precio}">
    </div>
  </div>



  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Editar</button>
    </div>
  </div>
</form>

           

</#macro>

<@display_page/>