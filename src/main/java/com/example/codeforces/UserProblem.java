package com.example.codeforces;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_problems")
public class UserProblem {
    @EmbeddedId
    private UserProblemId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("problemId")
    private ArchiveProblem problem;

    @ManyToOne
    @MapsId("contestId")
    private Contest contest;

    private String status;
    private Integer attempts;
    private LocalDateTime solvedAt;

    // Getters, Setters, Constructors
}
