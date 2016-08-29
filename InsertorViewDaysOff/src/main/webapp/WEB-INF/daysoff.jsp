<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: reitersg
  Date: 7/20/2016
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Day Off Date</title>
</head>
<body>
<h1>
    Day Off
</h1>
<form:form method="POST" action ="/web/addDayOff" commandName="Date">
    <form:select path="month">
        <option disabled selected value> - Month - </option>
        <option value="01">January</option>
        <option value="02">February</option>
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
<label>Purpose for day off</label>
    <form:input path="purpose" />
<input type="submit" value="Submit Date">
 </form:form>
<br>
<br>
<a href ="http://localhost:9090">Return to Home Page</a>
</body>
</html>
