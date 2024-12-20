package com.example.codeforces.db;

import jakarta.persistence.*;

@Entity
@Table(name = "user_contests")
public class UserContest {
    @EmbeddedId
    private UserContestId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("contestId")
    private Contest contest;

    private String participationStatus;

    // Getters, Setters, Constructors
}
