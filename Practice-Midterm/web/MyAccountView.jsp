<%-- 
    Document   : MyAccountView
    Created on : 08-Nov-2018, 20:00:35
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
        <h1>Hi ${account.name}</h1>
        <h2>Balance : ${account.balance}</h2>
        <a href="Withdraw">Withdraw</a><br>
        <a href="Deposit">Deposit</a><br>
        <a href="History">History</a><br>
        <a href="Logout">Logout</a>
    </body>
</html>
