package com.example.codeforces.repository;

import com.example.codeforces.db.UserContest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserContestRepository extends JpaRepository<UserContest, Long> {
    List<UserContest> findByUserUserId(Long userId);
    List<UserContest> findByUserUsername(String username);
}