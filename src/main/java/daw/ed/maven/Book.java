
package daw.ed.maven;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;


public class Book 
{
    private ObjectId id;
    private String titulo;
    private String autor;
    private String precio;
    
    public Book ()
    {
        
    }
    public Book (ObjectId id,String titulo, String autor, String precio)
    {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
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


    /**
     * @return the id
     */
    public Object getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }
}


