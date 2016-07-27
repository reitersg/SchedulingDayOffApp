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
        for (int i = 0; i < date2.size() - 1; i++){
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
