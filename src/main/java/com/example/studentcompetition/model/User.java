package com.example.studentcompetition.model;

public class User {
    private Long id;
    private String username;
    private String password;
    private String studentId;
    private String email;

    public User() {
    }

    public User(Long id, String username, String password, String studentId, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.studentId = studentId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}