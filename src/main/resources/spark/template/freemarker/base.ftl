<#macro page_head>
    <title>Librería Marta</title>

    <link rel="icon" type="image/ico" href="/img/Library.ico" />
  
    <!-- enlace a boostrap -->
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.css" rel="stylesheet" media="screen">

    <!-- enlace a font-awesome -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

  <link rel="stylesheet" href="/css/styles.css">
  </#macro>


<#macro display_page>
  <!DOCTYPE html>
  <html>
  <head>
      <@page_head/>
    </head>
    <body>
        <div id="header">
            
        </div>
        <div id="titulo">
            <p>
            <h1>Librería</h1>
            </p>
        </div>

        <div class="navbar">
        <div class="navbar-inner">
        <ul class="nav">
 
            <li class="active"><a href="/"><i class="icon-th icon-black"></i> Read</a></li>
            <li ><a href="/book/create"><i class="icon-plus-sign icon-black"></i> Create</a></li>
 
                    </ul>
    </div>
    </div>
			
        <div id="content">
            <@content/>
        </div>

        <div id="footer">
            <p></p>
        </div>
    
    </body>
  </html>
</#macro>	

