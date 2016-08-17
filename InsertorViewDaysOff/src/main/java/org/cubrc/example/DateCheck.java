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
        return (sb.toString().contains(date));
    }
    public static boolean checkVacationAlreadyExists(String vacation) throws IOException{
        Query query = new Query();
        String vacations = query.selectVacation();
        StringBuilder sb = DaysOffParser.parseVacation(vacations);
        return sb.toString().contains(vacation);
    }
    public static boolean checkTimeAlreadyExists(String time) throws IOException{
        Query query = new Query();
        String times = query.selectTime();
        StringBuilder sb = DaysOffParser.parseTime(times);
        return sb.toString().contains(time);
    }
    public static boolean checkSameDate(Date date) {
        if (date.getDay().equals(date.getEndDay()) && date.getEndMonth().equals(date.getMonth()) && date.getEndYear().equals(date.getYear())){
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkDayOffEmpty(Date date){
        if (date.getMonth().equals("") || date.getDay().equals("") || date.getYear().equals("") || date.getPurpose().equals("")){
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkTimePeriodEmpty(Date date){
        if (date.getMonth().equals("") || date.getDay().equals("") || date.getYear().equals("") || date.getPurpose().equals("") || date.getStartTime().equals("") ||
                date.getStartTimePeriod().equals("") || date.getEndTime().equals("") || date.getEndTimePeriod().equals("")){
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkVacationEmpty(Date date){
        if (date.getMonth().equals("") || date.getDay().equals("") || date.getYear().equals("") || date.getEndDay().equals("") || date.getEndMonth().equals("") || date.getEndYear().equals("")){
            return true;
        } else {
            return false;
        }
    }
}
