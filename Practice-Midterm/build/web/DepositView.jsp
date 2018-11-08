<%-- 
    Document   : DepositView
    Created on : 08-Nov-2018, 21:51:19
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
        <h1>Deposit Page</h1>
        Name : ${account.name}
        Balance : ${account.balance}
        <form>
            <input type="number" name="Deposit" required><br>
            <input type="submit" value="Deposit"><br>
        </form>
        <a href="MyAccountView.jsp">Back</a>
    </body>
</html>
