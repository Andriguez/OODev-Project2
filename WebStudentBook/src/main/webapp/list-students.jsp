<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: brhig
  Date: 11/12/2025
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.webstudentbook.Model.Student" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
%>

<html>
<head>
    <title>Web Student Book</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<% List<Student> theStudents = (List<Student>)request.getAttribute("STUDENT_LIST"); %>
<body>
<div id="wrapper">
    <div id="header">
        <h2>ESILV Engineer School</h2>
        <p><a href="LogoutServlet">Logout</a></p>
    </div>
</div>
<div id="container">
    <div>
        <h3>List of Students</h3>
    </div>
    <div id="content">

        <table>
            <tr>
                <th>First Name </th>
                <th>Last Name</th>
                <th>Email </th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach var="tempStudent" items="${STUDENT_LIST}">
                <c:url var="EditLink" value="EditStudentServlet">
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>
                <c:url var="DeleteLink" value="EditStudentServlet">
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <c:url var="DeleteLink" value="StudentControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <tr>
                    <td>${tempStudent.firstName}</td>
                    <td>${tempStudent.lastName}</td>
                    <td>${tempStudent.email}</td>
                    <c:if test='${sessionScope.role == "instructor"}'>
                    <td><a href="${EditLink}">Edit</a></td>
                    <td><a href="${DeleteLink}">Delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test='${sessionScope.role == "instructor"}'>
        <div style="margin: 5px;">
            <c:url var="AddStudentLink" value="AddStudentServlet" />
                 <form action="${AddStudentLink}" method="get">
                <button>Add new student</button>
            </form>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>
