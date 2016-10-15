package org.cubrc.example;
import java.io.IOException;
import java.util.Calendar;

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

    public static boolean checkSameDate(Vacation vacation) {
        if (vacation.getDay().equals(vacation.getEndDay()) && vacation.getEndMonth().equals(vacation.getMonth()) && vacation.getEndYear().equals(vacation.getYear())){
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        java.util.Date today = c.getTime();

        c.set(Calendar.YEAR, Integer.parseInt(date.getYear()));
        c.set(Calendar.MONTH, Integer.parseInt(date.getMonth()));
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.getDay()));

        java.util.Date eventDate = c.getTime();
        if (eventDate.after(today)){
            return true;
        } else {
            return false;
        }
    }



}
