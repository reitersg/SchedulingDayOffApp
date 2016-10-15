package org.cubrc.example;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Created by reitersg on 7/20/2016.
 */

public class Date {
    private String day;
    private String month;
    private String year;
    private String purpose;

    public Date(){

    }
    public Date(String day, String month, String year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
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

    public String getPurpose() {
        return purpose;
    }
}
