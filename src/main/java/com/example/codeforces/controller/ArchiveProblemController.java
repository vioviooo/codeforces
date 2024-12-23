package com.example.codeforces.controller;

import com.example.codeforces.db.ArchiveProblem;
import com.example.codeforces.db.ArchiveProblemDetailDTO;
import com.example.codeforces.db.ProblemTest;
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

    @PostMapping
    public ResponseEntity<ArchiveProblem> addProblem(@RequestBody ArchiveProblem archiveProblem) {
        ArchiveProblem savedProblem = archiveProblemService.addProblem(archiveProblem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProblem);
    }

    @GetMapping
    public ResponseEntity<List<ArchiveProblem>> getAllProblems() {
        List<ArchiveProblem> problems = archiveProblemService.getAllProblems();
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchiveProblem> getProblemById(@PathVariable Long id) {
        return archiveProblemService.getProblemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/problem-description/{id}")
    public ResponseEntity<ArchiveProblemDetailDTO> getProblemDescriptionById(@PathVariable Long id) {
        return archiveProblemService.getDetailedProblemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/aboba/{id}")
    public ResponseEntity<Void> deleteArchiveProblem(@PathVariable Long id) {
        if (archiveProblemService.deleteArchiveProblem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/add-test")
    public ResponseEntity<Void> addProblemTest(@PathVariable Long id, @RequestBody ProblemTest test) {
        boolean success = archiveProblemService.addProblemTest(id, test);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
