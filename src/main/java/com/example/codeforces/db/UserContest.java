package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "user_contests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserContest {
    @EmbeddedId
    private UserContestId id;

    @ManyToOne
    @MapsId("userId")
    @JsonBackReference
    private User user;

    @ManyToOne
    @MapsId("contestId")
    @JsonBackReference
    private Contest contest;

    private String participationStatus;

    public UserContest() {}

    public UserContest(User user, Contest contest) {
        this.user = user;
        this.contest = contest;
        this.id = new UserContestId(user.getUserId(), contest.getContestId());
    }

    // Getters and setters
    public UserContestId getId() {
        return id;
    }

    public void setId(UserContestId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}
