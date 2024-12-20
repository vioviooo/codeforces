package com.example.codeforces.controller;

import com.example.codeforces.db.ArchiveProblem;
import com.example.codeforces.service.ArchiveProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archive-problems")
public class ArchiveProblemController {

    @Autowired
    private ArchiveProblemService archiveProblemService;

    // 1. Add a new problem
    @PostMapping
    public ResponseEntity<ArchiveProblem> addProblem(@RequestBody ArchiveProblem archiveProblem) {
        ArchiveProblem savedProblem = archiveProblemService.addProblem(archiveProblem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProblem);
    }

    // 2. Get all problems
    @GetMapping
    public ResponseEntity<List<ArchiveProblem>> getAllProblems() {
        List<ArchiveProblem> problems = archiveProblemService.getAllProblems();
        return ResponseEntity.ok(problems);
    }

    // 3. Get a specific problem by ID
    @GetMapping("/{id}")
    public ResponseEntity<ArchiveProblem> getProblemById(@PathVariable Long id) {
        return archiveProblemService.getProblemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
