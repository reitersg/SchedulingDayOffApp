package org.cubrc.example;


import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reitersg on 7/21/2016.
 */
public class Query {

    public void insertDate(String date){
        RestClient client  = new RestClient();
        client.setServerToUpdate();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"
                + "PREFIX to: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>"
                +"insert data { to:DateTime ero:has_text_value"+ date + "}";
        client.post("", query);
    }
    public String selectDate(){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                 "PREFIX to: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "select ?date where { to:DateTime ero:has_text_value ?date } ";
        String response = client.post("", query);
        return response;
    }
    public void deleteDate(String date){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX to: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "delete where { to:DateTime ero:has_text_value " + date +
                " } ";
        client.post("", query);
    }

}
