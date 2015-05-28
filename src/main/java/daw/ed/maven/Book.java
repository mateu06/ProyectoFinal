
package daw.ed.maven;

import com.mongodb.DBObject;


public class Book 
{
    private String titulo;
    private String autor;
    private String precio;
    
    public Book ()
    {
        
    }
    
    public Book (String titulo, String autor, String precio)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    
    public String getTitulo() 
    {
        return titulo;
    }

    
    public void setTitulo(String titulo) 
    {
        this.titulo = titulo;
    }

    
    public String getAutor() 
    {
        return autor;
    }

    
    public void setAutor(String autor) 
    {
        this.autor = autor;
    }

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    DBObject[] toDBObjectBook() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


