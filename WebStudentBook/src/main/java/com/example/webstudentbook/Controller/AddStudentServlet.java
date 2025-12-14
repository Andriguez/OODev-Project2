package com.example.webstudentbook.Controller;

import com.example.webstudentbook.Model.Student;
import com.example.webstudentbook.Model.StudentDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;

    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/studentdb";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            this.dataSource = getDataSource();
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public AddStudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession().getAttribute("role");
        if (!"instructor".equals(role)) {
            response.sendRedirect("StudentControllerServlet");
            return;
        }

        request.getRequestDispatcher("add-student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession().getAttribute("role");
        if (!"instructor".equals(role)) {
            response.sendRedirect("StudentControllerServlet");
            return;
        }
        String name = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student(name, lastname, email);
        studentDBUtil.addStudent(student);
        response.sendRedirect("StudentControllerServlet");

    }
}
