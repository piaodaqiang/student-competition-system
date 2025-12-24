package com.example.studentcompetition.model;

public class Participant {
    private Long id;
    private String name;
    private String college;
    private String className;
    private String studentId;
    private Long competitionId;

    public Participant() {
    }

    public Participant(Long id, String name, String college, String className, String studentId, Long competitionId) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.className = className;
        this.studentId = studentId;
        this.competitionId = competitionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }
}