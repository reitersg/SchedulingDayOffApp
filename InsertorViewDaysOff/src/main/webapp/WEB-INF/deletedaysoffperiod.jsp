<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: reitersg
  Date: 8/9/2016
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time Period off</title>
</head>
<body>
<h1>
    Time Off
</h1>
<form:form method="POST" action ="/web/deleteTimePeriod" commandName="Date">
    <form:select path="month">
        <option disabled selected value> - Month - </option>
        <option value="01">January</option>
        <option value="02">Febuary</option>
        <option value="03">March</option>
        <option value="04">April</option>
        <option value="05">May</option>
        <option value="06">June</option>
        <option value="07">July</option>
        <option value="08">August</option>
        <option value="09">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
    </form:select>
    <br>
    <br>
    <form:select path="day">
        <option disabled selected value> - Day - </option>
        <option value="01">1</option>
        <option value="02">2</option>
        <option value="03">3</option>
        <option value="04">4</option>
        <option value="05">5</option>
        <option value="06">6</option>
        <option value="07">7</option>
        <option value="08">8</option>
        <option value="09">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        <option value="13">13</option>
        <option value="14">14</option>
        <option value="15">15</option>
        <option value="16">16</option>
        <option value="17">17</option>
        <option value="18">18</option>
        <option value="19">19</option>
        <option value="20">20</option>
        <option value="21">21</option>
        <option value="22">22</option>
        <option value="23">23</option>
        <option value="24">24</option>
        <option value="25">25</option>
        <option value="26">26</option>
        <option value="27">27</option>
        <option value="28">28</option>
        <option value="29">29</option>
        <option value="30">30</option>
        <option value="31">31</option>
    </form:select>
    <br>
    <br>
    <form:select path="year">
        <option disabled selected value> - Year - </option>
        <option value="2016">2016</option>
        <option value="2017">2017</option>
        <option value="2018">2018</option>
    </form:select>
    <br>
    <br>
    <form:select path="startTime">
        <option disabled selected value> - Start Time - </option>
        <option value = "12:00">12:00</option>
        <option value = "12:30">12:30</option>
        <option value = "1:00">1:00</option>
        <option value = "1:30">1:30</option>
        <option value = "2:00">2:00</option>
        <option value = "2:30">2:30</option>
        <option value = "3:00">3:00</option>
        <option value = "3:30">3:30</option>
        <option value = "4:00">4:00</option>
        <option value = "4:30">4:30</option>
        <option value = "5:00">5:00</option>
        <option value = "5:30">5:30</option>
        <option value = "6:00">6:00</option>
        <option value = "6:30">6:30</option>
        <option value = "7:00">7:00</option>
        <option value = "7:30">7:30</option>
        <option value = "8:00">8:00</option>
        <option value = "8:30">8:30</option>
        <option value = "9:00">9:00</option>
        <option value = "9:30">9:30</option>
        <option value = "10:00">10:00</option>
        <option value = "10:30">10:30</option>
        <option value = "11:00">11:00</option>
        <option value = "11:30">11:30</option>
    </form:select>
    <form:select path="startTimePeriod">
        <option disabled selected value></option>
        <option value="AM">AM</option>
        <option value="PM">PM</option>
    </form:select>
    <br>
    <br>
    <form:select path="endTime">
        <option disabled selected value> - End Time - </option>
        <option value = "12:00">12:00</option>
        <option value = "12:30">12:30</option>
        <option value = "1:00">1:00</option>
        <option value = "1:30">1:30</option>
        <option value = "2:00">2:00</option>
        <option value = "2:30">2:30</option>
        <option value = "3:00">3:00</option>
        <option value = "3:30">3:30</option>
        <option value = "4:00">4:00</option>
        <option value = "4:30">4:30</option>
        <option value = "5:00">5:00</option>
        <option value = "5:30">5:30</option>
        <option value = "6:00">6:00</option>
        <option value = "6:30">6:30</option>
        <option value = "7:00">7:00</option>
        <option value = "7:30">7:30</option>
        <option value = "8:00">8:00</option>
        <option value = "8:30">8:30</option>
        <option value = "9:00">9:00</option>
        <option value = "9:30">9:30</option>
        <option value = "10:00">10:00</option>
        <option value = "10:30">10:30</option>
        <option value = "11:00">11:00</option>
        <option value = "11:30">11:30</option>
    </form:select>
    <form:select path="endTimePeriod">
        <option disabled selected value></option>
        <option value="AM">AM</option>
        <option value="PM">PM</option>
    </form:select>
    <br>
    <br>
    <label>Purpose for time off</label>
    <form:input path="purpose" />
    <input type="submit" value="Submit Date">
</form:form>
<br>
<br>
<a href = "/web/daysOffList.html">Click here to view your time periods</a>
<br>
<br>
<a href ="http://localhost:9090">Return to Home Page</a>
</body>
</html>