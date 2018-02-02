<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Simple Stock Quote App</title>
</head>
<body>
<h2>
    Please enter a stock symbol, from date, to date and an interval: <br>
</h2>

<P>Available data: GOOG, 08/19/2004 through 08/23/2004, open close or midday<br>
   AAPL, 01/07/2000 through 01/07/2000, hourly</P>

<form name="myform" action="servlets/StockSearch/" method="post">
    <table>
        <tr>
            <td>Stock Symbol :</td>  <td><input type="text" name="symbol" title="symbol"></td>
        </tr>
        <tr>
            <td> From Date :</td> <td><input type="text" name="from" title="from"></td>
        </tr>
        <tr>
            <td>To Date :</td> <td><input type="text" name="to" title="to"></td>
        </tr>
        <tr>
            <td>Interval :</td> <td><input type="radio" name="interval" value="Open Close" > Open Close
            <input type="radio" name="interval" value="Midday"> Midday
            <input type="radio" name="interval" value="Hourly"> Hourly</td>
        </tr>


    </table>
    <input type="SUBMIT" value="SEARCH">
    <input type="HIDDEN" name="submit" value="true">
</form>

</body>
</html>