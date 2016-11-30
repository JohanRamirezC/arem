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
    ArrayList<String> cedulas;
     String verdad;
    private String validar(ArrayList<String> cedulas,String name,String credit){
        verdad="";
        cedulas.forEach(cedula->{
               if(name.equals(cedula)){
                   if(validar.isValid(credit)){
                      verdad="El cliente puede comprar y tienen una tarjeta valida";
                   }else{
                      verdad="El cliente puede comprar pero no posee una tarjeta valida";
                   }
               }
           });
           if(verdad==null){
               verdad="No puede comprar el cliente";
           }
            return verdad;
    }
  public static void main(String[] args) {
   ArrayList<String> cedulas = new ArrayList<String>();
    validar=new CreditCardValidator(CreditCardValidator.AMEX+CreditCardValidator.VISA+CreditCardValidator.MASTERCARD+CreditCardValidator.DINERS);
        cedulas.add("1020758841");
        cedulas.add("41741678");
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
     get("/validar/:name/:credit", (request, response) -> {
           Main verificar=new Main();
            return verificar.validar(cedulas, request.params(":name"), request.params(":credit"));
        });

  }

}
