<%-- 
    Document   : WithdrawView
    Created on : 08-Nov-2018, 20:52:57
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
        <h1>WithDrawView Page</h1><hr>
        Name : ${account.name}<br>
        Balance : ${account.balance}<br>
        <form>
            <input type="number" name="Withdraw" required><br>
            <input type="submit" value="Withdraw"><br>
        </form>
        <a href="MyAccountView.jsp">Back</a>
    </body>
</html>
