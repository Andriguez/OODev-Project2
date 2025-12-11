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

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;
    int id;

    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/studentdb";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        try {
            this.dataSource = getDataSource();
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public EditStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentDBUtil.fetchStudent(id);
        request.setAttribute("Student", student);
        request.getRequestDispatcher("edit-student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String fn= request.getParameter("firstName");
        String ln= request.getParameter("lastName");
        String email = request.getParameter("email");
        Student student = new Student(id,fn,ln,email);
        studentDBUtil.updateStudent(student);
        response.sendRedirect("StudentControllerServlet");
    }
}
