package com.example.codeforces;

import jakarta.persistence.*;

@Entity
@Table(name = "problem_tags")
public class ProblemTag {
    @EmbeddedId
    private ProblemTagId id;

    @ManyToOne
    @MapsId("problemId")
    private ArchiveProblem problem;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    // Getters, Setters, Constructors
}

