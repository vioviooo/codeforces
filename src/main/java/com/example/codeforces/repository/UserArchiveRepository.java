package com.example.codeforces.repository;

import com.example.codeforces.db.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserArchiveRepository extends JpaRepository<UserProblem, Long> {
    List<UserProblem> findByUserUserId(Long userId);
    List<UserProblem> findByUserUsername(String username);
}