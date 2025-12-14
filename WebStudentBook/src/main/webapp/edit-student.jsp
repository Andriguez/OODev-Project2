<%--
  Created by IntelliJ IDEA.
  User: brhig
  Date: 11/12/2025
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test='${sessionScope.role != "instructor"}'>
    <c:redirect url="StudentControllerServlet"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <title>Edit a student</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>ESILV Engineer School</h2>
    </div>
</div>
<div id="container">
    <a href="StudentControllerServlet">Back to List</a>
    <h3> Edit a Student</h3>
    <form action="EditStudentServlet" method="post" class="form-layout form-centered">
        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="firstName" value="${Student.firstName}">
        </div>

        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastName" value="${Student.lastName}">
        </div>

        <div class="form-group">
            <label>Email:</label>
            <input type="text" name="email" value="${Student.email}">
        </div>

        <div class="form-actions">
            <button type="submit" class="saveBtn">Save</button>
        </div>
    </form>
    <div style="clear:both;"></div>
</div>
</body>
</html>
