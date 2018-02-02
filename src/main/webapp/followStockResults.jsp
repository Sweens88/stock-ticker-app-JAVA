<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="symbol" type="java.lang.String" scope="session">
    <c:set target='${symbol}'  value='${sessionScope.get("symbol")}'/>
</jsp:useBean>

<jsp:useBean id="stockList" type="java.util.ArrayList" scope="session">
    <c:set target='${stockList}'  value='${sessionScope.get("stockList")}'/>
</jsp:useBean>


<html>
<head>
    <title>Stock Watch List</title>
</head>
<body>
<h1>Stock Quotes</h1>

<c:out value="${symbol}"/> <br>

<h3>Stocks you already follow:</h3>
<table border="1">

    <tr><td>Symbol</td></tr>
<c:forEach items="${stockList}" var="stock">
    <tr>
        <td width="60" height="30">${stock}</td>
    </tr>
</c:forEach>
</table>



</body>
</html>