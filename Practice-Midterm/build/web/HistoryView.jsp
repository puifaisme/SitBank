<%-- 
    Document   : HistoryView
    Created on : 08-Nov-2018, 21:58:20
    Author     : Chonticha Sae-jiw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>HistoryView Page</h1><hr>
       Name : ${account.name}<br>
    <table border="1">
        <tr>
            <th>#</th>
            <th>Time</th>
            <th>Method</th>
            <th>Amount</th>
            <th>Balance</th>
        </tr>
        <c:forEach items="${history}" var="h" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${h.time}</td>
                <td>${h.method}</td>
                <td>${h.amount}</td>
                <td>${h.balance}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="MyAccountView.jsp">Back</a>
    </body>
</html>
