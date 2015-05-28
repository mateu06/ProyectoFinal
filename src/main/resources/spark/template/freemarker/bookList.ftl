<#include "base.ftl">

<#macro content>

<table class="table table-hover">
  <tr>
    <thead>
                      
      <th>TÍTULO <i class="fa fa-book"></i></th> 
      <th>AUTOR <i class="fa fa-user"></i></th>
      <th>PRECIO <i class="fa fa-euro"></i></th>
      <th>ACCIÓN <i class="fa fa-gears"></i></th>
                      
    </thead>

  </tr>  

  <#list booksAL as libro>
    <tr>
      <td>${libro.titulo}</td>
      <td>${libro.autor}</td>
      <td>${libro.precio}</td>

      <td>
        <p>
        <a href="/book/edit/${libro.id}" class="btn btn-info boton1"><i class="fa fa-pencil"></i></a>
        <a href="book/remove/${libro.id}" class="btn btn-danger boton2"><i class="fa fa-trash-o"></i></a>
      </p> 
      </td> 
    </tr>
  </#list> 

 

</table>

           

</#macro>

<@display_page/>
