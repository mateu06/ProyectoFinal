<#include "base.ftl">

<#macro content>

<table class="table table-hover">
  <tr>
    <thead>
                      
      <th>TÍTULO</th> 
      <th>AUTOR</th>
      <th>PRECIO</th>
      <th>ACCIÓN</th>
                      
    </thead>

  </tr>  

  <#list booksAL as libro>
    <tr>
      <td>${libro.titulo}</td>
      <td>${libro.autor}</td>
      <td>${libro.precio}</td>
      <td>
        <p>
        <button type="button" class="btn btn-info">Editar</button>
        <button type="button" class="btn btn-danger">Borrar</button>
      </p> 
      </td> 
    </tr>
  </#list> 

 

</table>

           

</#macro>

<@display_page/>
