package org.cubrc.example;

import java.io.IOException;

/**
 * Created by reitersg on 8/3/2016.
 */
public class DateCheck {

    public static boolean checkHoliday(Date date) {
        int month = Integer.parseInt(date.getMonth());
        int day = Integer.parseInt(date.getDay());
        if ((month == 07) && (day == 04)){
            return true;
        } else if (month == 12 && day == 25){
            return true;
        } else if (month == 01 && day == 01){
            return true;
        } else if (month == 05 && day == 31){
            return true;
        } else if (month == 11 && day == 24){
            return true;
        }  else if (month == 11 && day == 11){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean checkAlreadyExists(String date) throws IOException{
        Query query = new Query();
        String dates = query.selectDate();
        StringBuilder sb = DaysOffParser.parseDate(dates);
        return sb.toString().contains(date);
    }
}
