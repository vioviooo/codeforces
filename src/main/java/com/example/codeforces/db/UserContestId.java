package com.example.codeforces.db;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserContestId implements Serializable {
    private Long userId;
    private Long contestId;

    public UserContestId() {}

    public UserContestId(Long userId, Long contestId) {
        this.userId = userId;
        this.contestId = contestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContestId that = (UserContestId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(contestId, that.contestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contestId);
    }
}
