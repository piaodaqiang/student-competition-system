package com.example.studentcompetition.model;

public class Competition {

    private Long id;
    private String name;
    private String level;

    public Competition(Long id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}
