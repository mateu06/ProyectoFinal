package daw.ed.maven;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
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

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        //Conexión con la base de datos libreria y con la colección libros

        MongoClient cliente = new MongoClient();

        //Recuperamos la base de datos libreria
        DB baseDeDatos = cliente.getDB("libreria");

        //Recuperamos los valores de la colección previamente introducidos:
        final DBCollection coleccion = baseDeDatos.getCollection("libros");

        MongoDatabase database = cliente.getDatabase("libreria");
        final MongoCollection<Document> coleccion2 = database.getCollection("libros");

        final ArrayList<Book> booksAL = new ArrayList<>();

        Spark.staticFileLocation("/public");

        //LISTAR LOS LIBROS
        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {

                //DBCursor();
                DBCursor cursor = coleccion.find();
                try {
                    while (cursor.hasNext()) {

                        BasicDBObject obj = (BasicDBObject) cursor.next();
                        booksAL.add(new Book(obj.getString("titulo"), obj.getString("autor"), obj.getString("precio")));
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

                coleccion2.insertOne(doc);

                response.redirect("/");
                return null;
            }

        });

    }


}
