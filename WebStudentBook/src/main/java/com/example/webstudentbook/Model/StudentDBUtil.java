package com.example.webstudentbook.Model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDBUtil {
    private final DataSource dataSource;

    public StudentDBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> students= new ArrayList<Student>();
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from student order by last_name";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                int id = myRs.getInt("id");
                String firstName=myRs.getString("first_name");
                String lastName=myRs.getString("last_name");
                String email = myRs.getString("email");
                Student tempStudent= new Student(id,firstName,lastName,email);
                students.add(tempStudent);
            }
            return students;
        } finally{
            close(myConn,myStmt,myRs);
        }
    }

    public Student fetchStudent(int id) {
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        Student student=null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from student where id="+id;
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                String firstName=myRs.getString("first_name");
                String lastName=myRs.getString("last_name");
                String email = myRs.getString("email");
                student = new Student(id,firstName,lastName,email);
            }
            return student;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void updateStudent(Student student) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "update student set first_name=?, last_name=?,email=? where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, student.getFirstName());
            myStmt.setString(2, student.getLastName());
            myStmt.setString(3, student.getEmail());
            myStmt.setInt(4, student.getId());
            myStmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,null);
        }
    }

    /**
     * Authenticate user from the database
     * @param username the username
     * @param password the password
     * @return User object if credentials are valid, null otherwise
     * @throws Exception if database error occurs
     */
    public User authenticateUser(String username, String password) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        User user = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from users where username=? and password=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, username);
            myStmt.setString(2, password);
            myRs = myStmt.executeQuery();

            if(myRs.next()) {
                String role = myRs.getString("role");
                user = new User(username, password, role);
            }

            return user;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if(myStmt!=null)
                myStmt.close();
            if(myRs!=null)
                myRs.close();
            if(myConn!=null)
                myConn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}