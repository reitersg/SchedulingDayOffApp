package org.cubrc.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
             dateDate = date3.get("date");
             dateValue = dateDate.get("value");
             String date = dateValue.asText();
             sb.append(date + ", ");
         }
         sb.append(date2.get(date2.size() - 1).get("date").get("value").asText());
         return sb;
     }
    }
    public static StringBuilder parsePurpose(String jsonPurpose) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StringBuilder sb = new StringBuilder();
        JsonNode node = mapper.readValue(jsonPurpose, JsonNode.class);

        JsonNode results = node.get("results");
        JsonNode bindings = results.get("bindings");
        JsonNode date3;
        JsonNode purposeValue;
        JsonNode purpose;
        if (bindings.size() == 0){
            return new StringBuilder();
        } else {
            for (int i = 0; i < bindings.size() - 1; i++) {
                date3 = bindings.get(i);
                purpose = date3.get("purpose");
                purposeValue = purpose.get("value");
                String individualPurposes = purposeValue.asText();
                sb.append(individualPurposes + ", ");
            }
            sb.append(bindings.get(bindings.size() - 1).get("purpose").get("value").asText());
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
        JsonNode timeValue;
        JsonNode time;
        if (bindings.size() == 0){
            return new StringBuilder();
        } else {
            for (int i = 0; i < bindings.size() - 1; i++) {
                temp = bindings.get(i);
                time = temp.get("time");
                timeValue = time.get("value");
                String timeTemp = timeValue.asText();
                sb.append(timeTemp + ", ");
            }
            sb.append(bindings.get(bindings.size() - 1).get("time").get("value").asText());
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
}
