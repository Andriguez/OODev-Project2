package com.example.webstudentbook.Controller;

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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private DataSource dataSource;
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/studentdb" ;
        Context context = new InitialContext();
        return (DataSource) context.lookup(jndi);
    }

    public void init() {
        try {
            dataSource = getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // TODO Auto-generated method stub
        //Step1: set up the printwriter
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        //Step2: Get a connection to the database
        Connection myConn=null;
        Statement myStmt= null;
        ResultSet myRs=null;
        try{
            myConn= dataSource.getConnection();
            //Step3: create SQL statements
            String sql = "select * from student";
            myStmt= myConn.createStatement();
            //Step4: Execute SQL query
            myRs=myStmt.executeQuery(sql);
            //Step5: Process the ResultSet
            while(myRs.next()){
                String email = myRs.getString("email");
                out.println(email);
            }
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }
}
