<%--
  Created by IntelliJ IDEA.
  User: brhig
  Date: 14/12/2025
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Access Restricted</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>ESILV Engineer School</h2>
    </div>
</div>

<div id="container">

    <div class="access-box">
        <h3>Access Restricted</h3>

        <p>
            Access to
            <strong>
                <c:out value="${restrictedPage}" default="this page"/>
            </strong>
            is restricted for this user.
        </p>

        <a href="StudentControllerServlet" class="back-link">
            ← Back to Student List
        </a>
    </div>

</div>

</body>
</html>
