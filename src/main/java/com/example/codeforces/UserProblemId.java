package com.example.codeforces;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserProblemId implements Serializable {
    private Long userId;
    private Long problemId;
    private Long contestId;

    // equals(), hashCode(), Getters, Setters
}
