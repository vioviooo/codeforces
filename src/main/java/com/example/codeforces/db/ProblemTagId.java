package com.example.codeforces.db;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProblemTagId implements Serializable {
    private Long problemId;
    private Long tagId;

    // equals(), hashCode(), Getters, Setters
}
