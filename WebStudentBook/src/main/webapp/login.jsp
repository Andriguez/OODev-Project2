<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 12/11/2025
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login - Web Student Book</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>ESILV Engineer School</h2>
    </div>
</div>

<div id="container">
    <h3>Login</h3>

    <%
        String error = (String)request.getAttribute("error");
        if(error != null) {
    %>
    <p style="color:red; font-weight:bold;"><%= error %></p>
    <% } %>

    <form action="LoginServlet" method="post">
        <table>
            <tbody>
            <tr>
                <td><label>Username: </label></td>
                <td><input type="text" name="username" value="${cookie.username.value}" required/></td>
            </tr>
            <tr>
                <td><label>Password: </label></td>
                <td><input type="password" name="password" required/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Login" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
