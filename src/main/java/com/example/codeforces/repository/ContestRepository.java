package com.example.codeforces.repository;

import com.example.codeforces.db.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {
}
