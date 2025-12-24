package com.example.studentcompetition.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Competition {

    private Long id;
    private String name;
    private String level;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date time;
    private int participantCount;
    private String location;
    private int winnerCount;
    private List<Participant> participants;

    public Competition() {
    }

    public Competition(Long id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Competition(Long id, String name, String level, Date time, int participantCount, String location, int winnerCount) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.time = time;
        this.participantCount = participantCount;
        this.location = location;
        this.winnerCount = winnerCount;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(int winnerCount) {
        this.winnerCount = winnerCount;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
