package org.cubrc.example;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by reitersg on 7/20/2016.
 */

public class Date {
    private String day;
    private String month;
    private String year;
    @JsonProperty("value")
    private String value;


    public void setDay(String day){
        this.day = day;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public void setYear(String year){
        this.year = year;
    }
    public void setValue(String value) { this.value = value;}

    public String getDay(){
        return this.day;
    }
    public String getMonth(){
        return this.month;
    }
    public String getYear(){
        return this.year;
    }
    public String toString(){
        return (year + "-" + month + "-" + day);
    }
    public String getValue(){
        return this.value;
    }
}
