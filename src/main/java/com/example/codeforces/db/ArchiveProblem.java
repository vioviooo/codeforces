package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("reference-archiveproblem-userproblem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserProblem> userProblems;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("reference-problemtest-archiveproblem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProblemTest> problemTests;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("reference-contest-archiveproblem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ContestProblem> contestProblems = new HashSet<>();


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

    public List<ProblemTest> getProblemTests() {
        return problemTests;
    }

    public void setProblemTests(List<ProblemTest> problemTests) {
        this.problemTests = problemTests;
    }

    public List<UserProblem> getUserProblems() {
        return userProblems;
    }

    public void setUserProblems(List<UserProblem> userProblems) {
        this.userProblems = userProblems;
    }

    public Set<ContestProblem> getContestProblems() {
        return contestProblems;
    }

    public void setContestProblems(Set<ContestProblem> contestProblems) {
        this.contestProblems = contestProblems;
    }
}
