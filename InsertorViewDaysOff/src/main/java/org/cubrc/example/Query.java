package org.cubrc.example;


import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reitersg on 7/21/2016.
 */
public class Query {


    public void insertDate(Date date){
        RestClient client  = new RestClient();
        client.setServerToUpdate();
        System.out.println(date.toString());
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#> \n"+
                "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#> \n" +
                "PREFIX agent: <http://www.ontologylibrary.mil/CommonCore/Mid/AgentOntology#> \n" +
                "PREFIX info: <http://www.ontologylibrary.mil/CommonCore/Mid/InformationEntityOntology#> \n" +
                "insert data { \n" +
                "ex:DayOff rdf:type event:Act .\n" +
                "ex:DayOff ero:occurs_on ex:DateDay , \n" +
                "ex:DateMonth , \n" +
                "ex:DateYear ; \n" +
                "ex:DayOff info:prescribes ex:DateObjective" +
                "ex:DateDay rdf:type time:Day .\n" +
                "ex:DateDay ero:designator_annotation " + "\"" + date.getDay()+ "\"" + ".\n" +
                "ex:DateMonth rdf:type time:Month .\n" +
                "ex:DateMonth ero:designator_annotation " + "\"" + date.getMonth()+ "\"" + ".\n" +
                "ex:DateYear rdf:type time:Year .\n" +
                "ex:DateYear ero:designator_annotation " + "\"" + date.getYear()+ "\"" + ".\n" +
                "ex:DateObjective rdf:type agent:Objective .\n" +
                "ex:DateObjective info:prescribes " + "\"" + date.getPurpose() + "\"" + ".\n" +
                " } ";
        client.post("", query);
    }
    public void insertVacation(Vacation vacation){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String query = "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "insert data { \n" +
                ":Vacation rdf:type time:MultiDayTemporalIdentifier .\n" +
                ":Vacation ero:occurs_on" + "\"" + vacation.toString() + " - " + vacation.toStringEnd() + "\""
                + "}";
        client.post("", query);
    }

    public void insertTime(Time time){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String query  = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"+
                "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "PREFIX agent: <http://www.ontologylibrary.mil/CommonCore/Mid/AgentOntology#>" +
                "PREFIX info: <http://www.ontologylibrary.mil/CommonCore/Mid/InformationEntityOntology#>" +
                "insert data { \n" +
                "ex:TimeOff rdf:type event:Act .\n" +
                "ex:TimeOff ero:occurs_on ex:TimeDay , \n" +
                "ex:TimeMonth , \n" +
                "ex:TimeYear , \n" +
                "ex:TimeTime ;" +
                "ex:TimeOff info:prescribes ex:TimeObjective" +
                "ex:TimeDay rdf:type time:Day .\n" +
                "ex:TimeDay ero:designator_annotation " + "\"" + time.getDay()+ "\"" + ".\n" +
                "ex:TimeMonth rdf:type time:Month .\n" +
                "ex:TimeMonth ero:designator_annotation " + "\"" + time.getMonth()+ "\"" + ".\n" +
                "ex:TimeYear rdf:type time:Year" +
                "ex:TimeYear ero:designator_annotation " + "\"" + time.getYear()+ "\"" + ".\n" +
                "ex:TimeTime rdf:type time:MultiHourTemporalIdentifier . \n" +
                "ex:TimeTime ero:designator_annotation" + "\"" + time.getTime() + "\"" + ".\n" +
                "ex:TimeObjective rdf:type agent:Objective . \n" +
                "ex:TimeObjective info:prescribes " + "\"" + time.getPurpose() + "\"" + ".\n" +
                " } ";
        client.post("", query);
    }
    public String selectDate(){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"
                + "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "PREFIX agent: <http://www.ontologylibrary.mil/CommonCore/Mid/AgentOntology#>" +
                "PREFIX info: <http://www.ontologylibrary.mil/CommonCore/Mid/InformationEntityOntology#>" +
                "select ?day ?month ?year ?purpose {\n" +
                "ex:DayOff rdf:type event:Act .\n" +
                "ex:DayOff ero:occurs_on ex:DateDay , \n" +
                "ex:DateMonth , \n" +
                "ex:DateYear ; \n" +
                "ex:DayOff info:prescribes ex:DateObjective" +
                "ex:DateDay rdf:type time:Day .\n" +
                "ex:DateDay ero:designator_annotation ?day . \n" +
                "ex:DayMonth rdf:type time:Month .\n" +
                "ex:DateMonth ero:designator_annotation ?month . \n" +
                "ex:DateYear rdf:type time:Year .\n" +
                "ex:DateYear ero:designator_annotation ?year . \n" +
                "ex:DateObjective rdf:type agent:Objective .\n" +
                "ex:DateObjective info:prescribes ?purpose" + ". \n" +
                "}";
        String response = client.post("", query);
        return response;
    }
    public String selectVacation(){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "select ?vacation where {time:MultiDayTemporalIdentifier ero:occurs_on ?vacation } ";
        String response = client.post("", query);
        return response;
    }

    public String selectTime(){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"
                + "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "PREFIX agent: <http://www.ontologylibrary.mil/CommonCore/Mid/AgentOntology#>" +
                "PREFIX info: <http://www.ontologylibrary.mil/CommonCore/Mid/InformationEntityOntology#>" +
                "select ?day ?month ?year ?purpose { \n" +
                "ex:TimeOff rdf:type event:Act .\n" +
                "ex:TimeOff ero:occurs_on ex:TimeDay , \n" +
                "ex:TimeMonth , \n" +
                "ex:TimeYear , \n" +
                "ex:TimeTime ;" +
                "ex:TimeOff info:prescribes ex:TimeObjective" +
                "ex:TimeDay rdf:type time:Day .\n" +
                "ex:TimeDay ero:designator_annotation ?day . \n" +
                "ex:TimeMonth rdf:type time:Month .\n" +
                "ex:TimeMonth ero:designator_annotation ?month . \n" +
                "ex:DateYear rdf:type time:Year .\n" +
                "ex:DateYear ero:designator_annotation ?year . \n" +
                "ex:TimeTime rdf:type time:MultiHourTemporalIdentifier . \n" +
                "ex:TimeTime ero:designator_annotation ?time" + ".\n" +
                "ex:TimeObjective rdf:type agent:Objective . \n" +
                "ex:TimeObjective info:prescribes ?purpose" + ".\n" +
                         "}";
        String response = client.post("", query);
        return response;
    }
    public void deleteDate(Date date){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String dateQuery = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"+
                "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "PREFIX agent: <http://www.ontologylibrary.mil/CommonCore/Mid/AgentOntology#>" +
                "PREFIX info: <http://www.ontologylibrary.mil/CommonCore/Mid/InformationEntityOntology#>" +
                "delete where {\n" +
                "ex:DayOff rdf:type event:Act .\n" +
                "ex:DayOff ero:occurs_on ex:DateDay , \n" +
                "ex:DateMonth , \n" +
                "ex:DateYear ; \n" +
                "ex:DayOff info:prescribes ex:DateObjective" +
                "ex:DateDay rdf:type time:Day .\n" +
                "ex:DateDay ero:designator_annotation " + "\"" + date.getDay()+ "\"" + ".\n" +
                "ex:DateMonth rdf:type time:Month .\n" +
                "ex:DateMonth ero:designator_annotation " + "\"" + date.getMonth()+ "\"" + ".\n" +
                "ex:DateYear rdf:type time:Year .\n" +
                "ex:DateYear ero:designator_annotation " + "\"" + date.getYear()+ "\"" + ".\n" +
                "ex:DateObjective rdf:type agent:Objective .\n" +
                "ex:DateObjective info:prescribes " + "\"" + date.getPurpose() + "\"" + ".\n" +
                " } ";
        client.post("", dateQuery);

    }
    public void deleteVacation(Vacation vacation){
        RestClient client = new RestClient();
        client.setServerToQuery();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>" +
                "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "delete where {time:MultiDayTemporalIdentifier ero:occurs_on"+ "\"" + vacation.toString() + " - " + vacation.toStringEnd() + "\"" + " } ";
        String response = client.post("", query);
    }


    public void deleteTime(Time time){
        RestClient client = new RestClient();
        client.setServerToUpdate();
        String query = "PREFIX ero: <http://www.ontologylibrary.mil/CommonCore/Upper/ExtendedRelationOntology#>"+
                "PREFIX time: <http://www.ontologylibrary.mil/CommonCore/Mid/TimeOntology#>" +
                "PREFIX agent: <http://www.ontologylibrary.mil/CommonCore/Mid/AgentOntology#>" +
                "PREFIX info: <http://www.ontologylibrary.mil/CommonCore/Mid/InformationEntityOntology#>" +
                "delete where { \n" +
                "ex:TimeOff rdf:type event:Act .\n" +
                "ex:TimeOff ero:occurs_on ex:TimeDay , \n" +
                "ex:TimeMonth , \n" +
                "ex:TimeYear , \n" +
                "ex:TimeTime ;" +
                "ex:TimeOff info:prescribes ex:TimeObjective" +
                "ex:TimeDay rdf:type time:Day .\n" +
                "ex:TimeDay ero:designator_annotation " + "\"" + time.getDay()+ "\"" + ".\n" +
                "ex:TimeMonth rdf:type time:Month .\n" +
                "ex:TimeMonth ero:designator_annotation " + "\"" + time.getMonth()+ "\"" + ".\n" +
                "ex:TimeYear rdf:type time:Year" +
                "ex:TimeYear ero:designator_annotation " + "\"" + time.getYear()+ "\"" + ".\n" +
                "ex:TimeTime rdf:type time:MultiHourTemporalIdentifier . \n" +
                "ex:TimeTime ero:designator_annotation" + "\"" + time.getTime() + "\"" + ".\n" +
                "ex:TimeObjective rdf:type agent:Objective . \n" +
                "ex:TimeObjective info:prescribes " + "\"" + time.getPurpose() + "\"" + ".\n" +
                " } ";
        String response = client.post("", query);
    }

}
