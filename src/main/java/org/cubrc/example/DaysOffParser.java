package org.cubrc.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.util.resources.cldr.ak.CalendarData_ak_GH;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by reitersg on 7/22/2016.
 */
public class DaysOffParser {

    public static StringBuilder parseDate(String jsonDate) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StringBuilder sb = new StringBuilder();
        JsonNode node = mapper.readValue(jsonDate, JsonNode.class);

        JsonNode date1 = node.get("results");
        JsonNode date2 = date1.get("bindings");
        JsonNode date3;
        JsonNode dateValue;
        JsonNode dateDate;
     if (date2.size() == 0){
         return new StringBuilder();
     } else {
         for (int i = 0; i < date2.size() - 1; i++) {
             date3 = date2.get(i);
             String dateDay = date3.get("day").get("value").asText();
             String dateMonth = date3.get("month").get("value").asText();
             String dateYear = date3.get("year").get("value").asText();
             String purpose = date3.get("purpose").get("value").asText();
             Date date = new Date(dateDay, dateMonth, dateYear);
             date.setPurpose(purpose);
             sb.append(date.toString() + ": " + date.getPurpose() + ", ");
         }
         String dateDay = date2.get(date2.size() - 1).get("day").get("value").asText();
         String dateMonth = date2.get(date2.size() - 1).get("month").get("value").asText();
         String dateYear = date2.get(date2.size() - 1).get("year").get("value").asText();
         String purpose = date2.get(date2.size() - 1).get("purpose").get("value").asText();
         Date date = new Date(dateDay, dateMonth, dateYear);
         sb.append(date.toString() + ": " + purpose);
         return sb;
     }
    }

    public static StringBuilder parseTime(String jsonTime) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StringBuilder sb = new StringBuilder();
        JsonNode node = mapper.readValue(jsonTime, JsonNode.class);

        JsonNode results = node.get("results");
        JsonNode bindings = results.get("bindings");
        JsonNode temp;
        if (bindings.size() == 0){
            return new StringBuilder();
        } else {
            for (int i = 0; i < bindings.size() - 1; i++) {
                temp = bindings.get(i);
                String dateDay = temp.get("day").get("value").asText();
                String dateMonth = temp.get("month").get("value").asText();
                String dateYear = temp.get("year").get("value").asText();
                String time = temp.get("time").get("value").asText();
                String purpose = temp.get("purpose").get("value").asText();
                Date date = new Date(dateDay, dateMonth, dateYear);
                sb.append(date.toString() + ": " + time + ": " + purpose + ", ");
            }
            String dateDay = bindings.get(bindings.size() - 1).get("day").get("value").asText();
            String dateMonth = bindings.get(bindings.size() - 1).get("month").get("value").asText();
            String dateYear = bindings.get(bindings.size() - 1).get("year").get("value").asText();
            String time = bindings.get(bindings.size() - 1).get("time").get("value").asText();
            String purpose = bindings.get(bindings.size() - 1).get("purpose").get("value").asText();

            Date date = new Date(dateDay, dateMonth, dateYear);
            sb.append(date.toString() + ": " + time + ": " + purpose);
            return sb;
        }
    }
    public static StringBuilder parseVacation(String jsonVacation) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StringBuilder sb = new StringBuilder();
        JsonNode node = mapper.readValue(jsonVacation, JsonNode.class);

        JsonNode results = node.get("results");
        JsonNode bindings = results.get("bindings");
        JsonNode temp;
        JsonNode vacationValue;
        JsonNode vacation;
        if (bindings.size() == 0){
            return new StringBuilder();
        } else {
            for (int i = 0; i < bindings.size() - 1; i++) {
                temp = bindings.get(i);
                vacation = temp.get("vacation");
                vacationValue = vacation.get("value");
                String vacationTemp = vacationValue.asText();
                sb.append(vacationTemp + ", ");
            }
            sb.append(bindings.get(bindings.size() - 1).get("vacation").get("value").asText());
            return sb;
        }
    }
    public static int daysLeft(StringBuilder sb){


    return 0;
    }
}
