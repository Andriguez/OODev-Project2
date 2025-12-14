package com.example.webstudentbook.Controller;

import com.example.webstudentbook.Model.Student;
import com.example.webstudentbook.Model.StudentDBUtil;
import jakarta.servlet.RequestDispatcher;
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
import java.util.List;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/studentdb" ;
        Context context = new InitialContext();
        dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dataSource= getDataSource();
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String command = request.getParameter("command");

            if (command != null && command.equals("DELETE")){
                String role = (String) request.getSession().getAttribute("role");
                if ("instructor".equals(role)) {
                    deleteStudent(request,response);
                }
            } else {
                listStudents(request,response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<Student> students = studentDBUtil.getStudents();
        request.setAttribute("STUDENT_LIST", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int studentId = Integer.parseInt(request.getParameter("studentId"));

        studentDBUtil.deleteStudent(studentId);

        response.sendRedirect("StudentControllerServlet");
    }
}