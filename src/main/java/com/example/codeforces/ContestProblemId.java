package com.example.codeforces;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ContestProblemId implements Serializable {
    private Long contestId;
    private Long problemId;

    // equals(), hashCode(), Getters, Setters
}