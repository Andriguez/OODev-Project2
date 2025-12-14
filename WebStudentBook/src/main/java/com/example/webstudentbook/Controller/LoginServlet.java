package com.example.webstudentbook.Controller;

import com.example.webstudentbook.Model.StudentDBUtil;
import com.example.webstudentbook.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;

    public LoginServlet() {
        super();
    }

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
            dataSource = getDataSource();
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = studentDBUtil.authenticateUser(username, password);

            if(user != null) {

                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());


                Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(userCookie);

                // Redirect to student list
                response.sendRedirect("StudentControllerServlet");

            } else {
                request.setAttribute("error", "Invalid username or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "Database connection error!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}