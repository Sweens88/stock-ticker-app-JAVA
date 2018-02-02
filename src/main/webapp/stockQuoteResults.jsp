<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="stockQuoteList" type="java.util.ArrayList"  scope="session">
    <c:set target='${stockQuoteList}'  value='${sessionScope.get("stockQuoteList")}'/>
</jsp:useBean>


<html>
<head>
    <title>Stock Quotes</title>
</head>
<body>
<h1>Stock Quotes</h1>

<c:forEach items="${stockQuoteList}" var="quote">
    <tr>
        <td>${quote.toString()}</td><br>
    </tr>
</c:forEach>

</body>
</html>