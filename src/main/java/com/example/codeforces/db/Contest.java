package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "contests")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contestId; // Primary key field

    private String name;
    private LocalDateTime startTime;
    private Integer duration; // in minutes
    private String difficulty;

    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("reference2")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserContest> users = new HashSet<>();

    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("reference-contest-contestproblem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ContestProblem> contestProblems = new HashSet<>();

    // Default constructor
    public Contest() {}

    // Constructor with parameters
    public Contest(String name, LocalDateTime startTime, Integer duration, String difficulty) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    // Getters and Setters
    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Set<UserContest> getUsers() {
        return users;
    }

    public void setUsers(Set<UserContest> users) {
        this.users = users;
    }

    public Set<ContestProblem> getContestProblems() {
        return contestProblems;
    }

    public void setContestProblems(Set<ContestProblem> contestProblems) {
        this.contestProblems = contestProblems;
    }
}
