package org.cubrc.example;

/**
 * Created by reitersg on 10/4/2016.
 */
public class Vacation {
    private String day = "";
    private String month = "";
    private String year = "";
    private String endDay = "";
    private String endMonth = "";
    private String endYear = "";


    public void setDay(String day){
        this.day = day;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public void setYear(String year){
        this.year = year;
    }
    public void setEndDay(String endDay){this.endDay = endDay;}
    public void setEndMonth(String endMonth){this.endMonth = endMonth;}
    public void setEndYear(String endYear){this.endYear = endYear;}

    public String getDay(){
        return this.day;
    }
    public String getMonth(){
        return this.month;
    }
    public String getYear(){
        return this.year;
    }
    public String getEndDay(){return this.endDay;}
    public String getEndMonth(){return this.endMonth;}
    public String getEndYear(){return this.endYear;}
    public String toString(){
        return (year + "-" + month + "-" + day);
    }
    public String toStringEnd(){
        return (endYear + "-" + endMonth + "-" + endDay);
    }

}
