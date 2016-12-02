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
import org.apache.commons.validator.routines.CreditCardValidator;

public class Main {
    static ArrayList<String> personaEntrega;
    static ArrayList<String> facturas;
    static ArrayList<String> observacion;
    static ArrayList<String> personaRecibe;
 
  public static void main(String[] args) {
    ArrayList<String> facturas = new ArrayList<String>();
    ArrayList<String> personaEntrega = new ArrayList<String>();
    ArrayList<String> observacion = new ArrayList<String>();
    ArrayList<String> personaRecibe = new ArrayList<String>();
   
    facturas.add("1020758841");
    facturas.add("41741678");
    personaEntrega.add("kevin sanchez");
    personaEntrega.add("jose arboleda");
    observacion.add("ninguna");
    observacion.add("hay latas abolladas");
    personaRecibe.add("alexander ceron manco");
    personaRecibe.add("joseph arboleda");
    
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
     get("/:name", (request, response) -> {
         String v=null;
           for(int i=0;i<facturas.size();i++){
               if(request.params(":name").equals(facturas.get(i))){
                   v=" Id factura "+facturas.get(i)+"\n"
                     +" Persona que recibe : "+personaRecibe.get(i)+"\n"
                     +" Persona que entrega : "+personaEntrega.get(i)+"\n"
                     +" Observacion : "+observacion.get(i);
               }
           }
           if(v==null){
               v="No puede comprar el cliente";
           }
            return v;
        });

  }

}
