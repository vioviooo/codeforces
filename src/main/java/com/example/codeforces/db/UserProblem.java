//package com.example.codeforces.db;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "user_problems")
//public class UserProblem {
//    @EmbeddedId
//    private UserProblemId id;
//
//    @ManyToOne
//    @MapsId("userId")
//    @JsonBackReference
//    private User user;
//
//    @ManyToOne
//    @MapsId("problemId")
//    @JsonBackReference
//    private ArchiveProblem problem;
//
//    private String status;
//    private Integer attempts;
//    private LocalDateTime solvedAt;
//
//    // Getters, Setters, Constructors
//}
package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_problems")
public class UserProblem {
    @EmbeddedId
    private UserProblemId id;

    @ManyToOne
    @MapsId("userId")
    @JsonBackReference
    private User user;

    @ManyToOne
    @MapsId("problemId")
    @JsonBackReference
    private ArchiveProblem problem;

    private String status;
    private Integer attempts;
    private LocalDateTime solvedAt;

    // Default constructor
    public UserProblem() {
    }

    // Constructor with all fields
    public UserProblem(UserProblemId id, User user, ArchiveProblem problem, String status, Integer attempts, LocalDateTime solvedAt) {
        this.id = id;
        this.user = user;
        this.problem = problem;
        this.status = status;
        this.attempts = attempts;
        this.solvedAt = solvedAt;
    }

    // Getters and Setters
    public UserProblemId getId() {
        return id;
    }

    public void setId(UserProblemId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArchiveProblem getProblem() {
        return problem;
    }

    public void setProblem(ArchiveProblem problem) {
        this.problem = problem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getSolvedAt() {
        return solvedAt;
    }

    public void setSolvedAt(LocalDateTime solvedAt) {
        this.solvedAt = solvedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProblem that = (UserProblem) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
