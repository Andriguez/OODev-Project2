package com.example.webstudentbook.Model;

import java.beans.JavaBean;
import java.lang.annotation.Annotation;

@JavaBean
public class Student {
    private int  id;
    private String firstName, lastName, email;

    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // getters
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", FirstName: " + this.firstName + ", LastName: " + this.lastName + ", Email: " + this.email;
    }
}
