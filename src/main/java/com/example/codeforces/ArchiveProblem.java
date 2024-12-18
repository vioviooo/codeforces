package com.example.codeforces;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "archive_problems")
public class ArchiveProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId; // Primary key

    private String title;
    private String difficulty;
    private String statement;
    private Integer timeLimit; // in seconds
    private Integer memoryLimit; // in MB

    // Default constructor
    public ArchiveProblem() {}

    // Parameterized constructor
    public ArchiveProblem(String title, String difficulty, String statement, Integer timeLimit, Integer memoryLimit) {
        this.title = title;
        this.difficulty = difficulty;
        this.statement = statement;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    // Getters and Setters
    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }
}
