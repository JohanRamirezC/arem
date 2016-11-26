import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {
    static ArrayList<String> cedulas;
    static String verdad;
  public static void main(String[] args) {
    cedulas = new ArrayList<String>();
        cedulas.add("1020758841");
        cedulas.add("41741678");
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

     get("/prueba/:name", (request, response) -> {
           cedulas.forEach(cedula->{
               if(request.params(":name").equals(cedula)){
                   verdad="existe";
               }
           });
           if(verdad==null){
               verdad="no existe";
           }
            return "Hello: " + verdad;
        });

  }

}
