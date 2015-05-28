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


public class App {

    public static void main(String[] args) {

        //________________CONEXION CON LA BASE DE DATOS_________________________
        MongoClient cliente = new MongoClient();
        MongoDatabase baseDeDatos = cliente.getDatabase("libreria");
        final MongoCollection<Document> coleccion = baseDeDatos.getCollection("libros");

        final ArrayList<Book> booksAL = new ArrayList<>(); 

        Spark.staticFileLocation("/public");

        
        //______________LISTAR LIBROS DE LA BASE DE DATOS_______________________
        
        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {

                booksAL.clear(); //para poner a cero el array antes de recorrerlo
                MongoCursor<Document> cursor = coleccion.find().iterator();
                try {
                    while (cursor.hasNext()) {

                        Document obj = cursor.next();
                        booksAL.add(new Book(
                                (ObjectId) obj.get("_id"),
                                obj.getString("titulo"),
                                obj.getString("autor"),
                                obj.getString("precio")
                        ));
                    }
                } finally {
                    cursor.close();
                }

                Map<String, Object> data = new HashMap<>();
                data.put("booksAL", booksAL);
                return modelAndView(data, "bookList.ftl");
            }
        });

        
        
        //_____________AÑADIR LIBROS A LA BASE DE DATOS_________________________
        
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

                ObjectId id = (ObjectId) doc.get("_id");

                bk.setId(id);

                response.redirect("/");
                return null;
            }

        });
        
        
        //_________________ELIMINAR LIBROS DE LA BASE DE DATOS__________________
        
        get(new Route("/book/remove/:id") { //con "/:id" cojo la id del libro seleccionado y de este modo puedo borrarlo
            @Override

            public Object handle(Request request, Response response) {
                ObjectId id = new ObjectId(request.params(":id"));

                coleccion.deleteOne(eq("_id", id));

                response.redirect("/");
                return null;
            }
        });

        
        //________________EDITAR UN LIBRO DE LA BASE DE DATOS___________________
        
        get(new FreeMarkerRoute("/book/edit/:id") {
            @Override

            public ModelAndView handle(Request request, Response response) {
                ObjectId id = new ObjectId(request.params(":id"));

                Bson filtro = eq("_id", id); //busco el libro cuya id sea la misma q la de la ruta para pasarlo como filtro al find
                Document doc = coleccion.find(filtro).first();

                Book libro = new Book(
                        (ObjectId) doc.get("_id"),
                        doc.getString("titulo"),
                        doc.getString("autor"),
                        doc.getString("precio"));

                Map<String, Object> data = new HashMap<>();
                data.put("libro", libro);

                return modelAndView(data, "bookEdit.ftl");
            }

        });

        post(new Route("/book/edit/:id") {
            @Override

            public Object handle(Request request, Response response) {
                ObjectId id = new ObjectId(request.params(":id"));

                Book bk = new Book();
                bk.setTitulo(request.queryParams("titulo"));
                bk.setAutor(request.queryParams("autor"));
                bk.setPrecio(request.queryParams("precio"));

                coleccion.updateOne(eq("_id", id), new Document("$set", new Document()
                        .append("titulo", bk.getTitulo())
                        .append("autor", bk.getAutor())
                        .append("precio", bk.getPrecio())));

                response.redirect("/");
                return null;
            }

        });

    }

}
