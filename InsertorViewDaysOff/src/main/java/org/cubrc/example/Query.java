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
    public void insertPurpose(String purpose){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"+
                        "PREFIX ex: <http://www.example.ex/ontologyinstances#>"+
                "insert data {ex:Purpose ero:has_text_value" + "\"" + purpose + "\"" + "}";
        client.post("", query);
    }
    public void insertTime(String time){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"+
                "PREFIX to: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>"+
                "insert data {to:TimeOfDay ero:has_text_value" + time + "}";
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
    public String selectPurpose(){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX ex: <http://www.example.ex/ontologyinstances#>" +
                "select ?purpose where { ex:Purpose ero:has_text_value ?purpose } ";
        String response = client.post("", query);
        return response;
    }
    public String selectTime(){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX to: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "select ?time where { to:TimeOfDay ero:has_text_value ?time } ";
        String response = client.post("", query);
        return response;
    }
    public void deleteDate(Date date){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String dateQuery = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX to: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "delete where { to:DateTime ero:has_text_value " + "\"" + date.toString()+
                "\"" +
                " } ";
        String purposeQuery = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX ex: <http://www.example.ex/ontologyinstances#>" +
                "delete where { ex:Purpose ero:has_text_value" + "\"" + date.getPurpose()
                + "\"" +
                " } ";
        client.post("", dateQuery);
        client.post("", purposeQuery);
    }


}
