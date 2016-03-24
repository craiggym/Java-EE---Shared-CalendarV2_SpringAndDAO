<%--
  Created by IntelliJ IDEA.
  User: craig
  Date: 2/18/16
  Time: 1:49 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css"/>
</head>
<body>
    <h1>Sign up while it's free!</h1>
    <p style="color:darkred">Name already registered!</p>
    <br/>
    <br/>
    <form action="register" method="POST">
        <fieldset>
            <legend><em>Personal Information</em></legend><br/>
            <label>First name: </label><span> <input type="text" name="fname"></span>
            <br/><br/>
            <label>Last name: </label> <span> <input type="text" name="pass"></span>
                <br/><br/>
                <input type="submit" value="Submit">
        </fieldset>
    </form>
</body>
</html>
