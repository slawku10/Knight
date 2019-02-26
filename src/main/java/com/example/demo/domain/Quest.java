package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;
    private int reward = 100;
    protected int lengthInSeconds = 10;
    protected LocalDateTime startDate;
    private boolean started = false;
    private boolean completed = false;

    public Quest() {
    }

    public Quest(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Quest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getLength() {
        return lengthInSeconds;
    }

    public void setLength(int length) {
        this.lengthInSeconds = length;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        if (started) {
            this.startDate = LocalDateTime.now();
        }
        this.started = started;
    }

    public boolean isCompleted() {
        if (completed){
            return this.completed;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = this.startDate.plusSeconds(this.lengthInSeconds);
        boolean isAfter = now.isAfter(localDateTime);
        if (isAfter){
            this.completed = true;
        }
        return isAfter;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return description;
    }
}
