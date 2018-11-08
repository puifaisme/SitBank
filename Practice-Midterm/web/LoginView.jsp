<%-- 
    Document   : Login
    Created on : 08-Nov-2018, 19:54:06
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
        <h1>LoginView Page</h1><br><hr><br>
        <form action="Login" method="post">
        Account Id : <input type="number" name="AccountId" required><br>
        Pin : <input type="number" name="Password" required><br>
        <input type="submit" value="Login"><hr>
        </form>
        ${message}
    </body>
</html>
