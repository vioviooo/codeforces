package com.example.codeforces.repository;

import com.example.codeforces.db.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
