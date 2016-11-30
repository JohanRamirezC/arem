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
    static CreditCardValidator validar;
    static ArrayList<String> cedulas;
    
  public static void main(String[] args) {
   ArrayList<String> cedulas = new ArrayList<String>();
    validar=new CreditCardValidator(CreditCardValidator.AMEX+CreditCardValidator.VISA+CreditCardValidator.MASTERCARD+CreditCardValidator.DINERS);
        cedulas.add("1020758841");
        cedulas.add("41741678");
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
     get("/validar/:name/:credit", (request, response) -> {
         String v=null;
           for(int i=0;i<cedulas.size();i++){
               if(request.params(":name").equals(cedulas.get(i))){
                   if(validar.isValid(request.params(":credit"))){
                      v= "El cliente puede comprar y tienen una tarjeta valida";
                   }else{
                      v= "El cliente puede comprar pero no posee una tarjeta valida";
                   }
               }
           }
           if(v==null){
               v="No puede comprar el cliente";
           }
            return v;
        });

  }

}
