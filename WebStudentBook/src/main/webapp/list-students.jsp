<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: brhig
  Date: 11/12/2025
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.example.webstudentbook.jdbc.*" %>
<%@ page import="com.example.webstudentbook.Model.Student" %>

<html>
<head>
    <title>Web Student Book</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<% List<Student> theStudents = (List<Student>)request.getAttribute("STUDENT_LIST"); %>
<body>
<div id="wrapper">
    <div id="header">
        <h2>List of SG Students</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>First Name </th>
                <th>Last Name</th>
                <th>Email </th>
            </tr>
   <c:forEach var="tempStudent" items="${STUDENT_LIST}">
    <c:url var="DeleteLink" value="EditStudentServlet">
        <c:param name="studentId" value="${tempStudent.id}"/>
    </c:url>

    <c:forEach var="tempStudent" items="${STUDENT_LIST}">
                <c:url var="EditLink" value="EditStudentServlet">
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <tr>
                    <td>${tempStudent.firstName}</td>
                    <td>${tempStudent.lastName}</td>
                    <td>${tempStudent.email}</td>
                    <td><a href="${EditLink}"> Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
