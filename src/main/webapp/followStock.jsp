<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Title</title>
</head>
<h2>
    Enter the stock symbol for a stock you would like to follow, along with your information.<br>
</h2>
<body>

<form name="myform" action="servlets/FollowStock/" method="post">
    <table>
        <tr>
            <td>Stock Symbol :</td>  <td><input type="text" name="symbol" title="symbol"></td>
        </tr>
        <tr>
            <td>First Name :</td> <td><input type="text" name="firstName" title="firstName"></td>
        </tr>
        <tr>
            <td>Last Name :</td> <td><input type="text" name="lastName" title="lastName"></td>
        </tr>
    </table>
    <input type="SUBMIT" value="FOLLOW">
    <input type="HIDDEN" name="submit" value="true">
</form>



</body>
</html>
