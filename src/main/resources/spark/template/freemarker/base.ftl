<#macro page_head>
  <title>Librería Marta</title>
  
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.css" rel="stylesheet" media="screen">
  
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
            <p>
            <h1>LISTA</h1>
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

