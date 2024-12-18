package com.example.codeforces;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserContestId implements Serializable {
    private Long userId;
    private Long contestId;

    // equals(), hashCode(), Getters, Setters
}
