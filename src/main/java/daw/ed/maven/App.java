package daw.ed.maven;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;
import org.bson.Document;
import org.bson.conversions.Bson;
import spark.Route;
import static spark.Spark.post;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;



/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        MongoClient cliente = new MongoClient();
        MongoDatabase baseDeDatos = cliente.getDatabase("libreria");
        final MongoCollection<Document> coleccion = baseDeDatos.getCollection("libros");


        final ArrayList<Book> booksAL = new ArrayList<>();

        Spark.staticFileLocation("/public");

        //LISTAR LOS LIBROS
        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {

                //DBCursorListar();
                booksAL.clear();
                MongoCursor<Document> cursor = coleccion.find().iterator();
                try {
                    while (cursor.hasNext()) {

                        Document obj = cursor.next();
                        booksAL.add(new Book(
                                (ObjectId)obj.get( "_id"),
                                obj.getString("titulo"),
                                obj.getString("autor"),
                                obj.getString("precio")
                        ));
                        System.out.println(obj.getString("titulo"));
                    }
                } finally {
                    cursor.close();
                }
                
                
                
                
                Map<String, Object> data = new HashMap<>();
                data.put("booksAL", booksAL);
                return modelAndView(data, "bookList.ftl");
            }
        });

        get(new FreeMarkerRoute("/book/create") {
            @Override
            public ModelAndView handle(Request request, Response response) {

                return modelAndView(null, "bookCreate.ftl");
            }
        });

        post(new Route("/book/create") {
            @Override
            public Object handle(Request request, Response response) {
                Book bk = new Book();
                bk.setTitulo(request.queryParams("titulo"));
                bk.setAutor(request.queryParams("autor"));
                bk.setPrecio(request.queryParams("precio"));

                Document doc = new Document();
                doc.append("titulo", bk.getTitulo());
                doc.append("autor", bk.getAutor());
                doc.append("precio", bk.getPrecio());

                coleccion.insertOne(doc);
                
                ObjectId id = (ObjectId)doc.get( "_id" );
                
                bk.setId(id);

                response.redirect("/");
                return null;
            }

        });
        
        get (new Route ("/book/remove/:id"){
                @Override
                
                    public Object handle (Request request, Response response)
                    {                                             
                        ObjectId id = new ObjectId (request.params(":id"));
                        
                        coleccion.deleteOne(eq("_id",id));

                        response.redirect("/");
                        return null;
                    }
                });
        
       

    }


}
/*
Book bk = new Book();
                        
                        Bson filtro = new Document("id",bk.getId()); 
                        
                        coleccion.deleteOne(filtro);
                        response.redirect("/");
*/