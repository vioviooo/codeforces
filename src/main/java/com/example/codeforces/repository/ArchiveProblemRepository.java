package com.example.codeforces.repository;

import com.example.codeforces.db.ArchiveProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveProblemRepository extends JpaRepository<ArchiveProblem, Long> {
}
