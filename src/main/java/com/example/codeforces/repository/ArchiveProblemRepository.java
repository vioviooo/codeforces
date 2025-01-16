package com.example.codeforces.repository;

import com.example.codeforces.db.ArchiveProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ArchiveProblemRepository extends JpaRepository<ArchiveProblem, Long> {
    @Query(value = "SELECT * FROM archive_problems ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    ArchiveProblem findRandomProblem();
}
