package org.cubrc.example;

/**
 * Created by reitersg on 10/3/2016.
 */
public class Time {
    private String day;
    private String month;
    private String year;
    private String purpose;
    private String startTime;
    private String endTime;
    private String startTimePeriod;
    private String endTimePeriod;

    public void setDay(String day){
        this.day = day;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public void setYear(String year){
        this.year = year;
    }

    public void setPurpose(String purpose){this.purpose = purpose;}
    public void setStartTime(String startTime){this.startTime = startTime;}
    public void setEndTime(String endTime){this.endTime = endTime;}
    public void setStartTimePeriod(String startTimePeriod){this.startTimePeriod = startTimePeriod;}
    public void setEndTimePeriod(String endTimePeriod){this.endTimePeriod = endTimePeriod;}

    public String getDay(){
        return this.day;
    }
    public String getMonth(){
        return this.month;
    }
    public String getYear(){
        return this.year;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public String getStartTimePeriod(){
        return this.startTimePeriod;
    }
    public String getEndTimePeriod(){
        return this.endTimePeriod;
    }
    public String getTime(){ return startTime + " " + startTimePeriod + " - " + endTime + " " + endTimePeriod;}

    public String getPurpose() {
        return purpose;
    }
}
