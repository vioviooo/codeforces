package com.example.codeforces;

import jakarta.persistence.*;

@Entity
@Table(name = "contest_problems")
public class ContestProblem {
    @EmbeddedId
    private ContestProblemId id;

    @ManyToOne
    @MapsId("contestId")
    private Contest contest;

    @ManyToOne
    @MapsId("problemId")
    private ArchiveProblem problem;

    // Getters, Setters, Constructors
}
