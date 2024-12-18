package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchiveProblemService {

    @Autowired
    private ArchiveProblemRepository archiveProblemRepository;

    // Add a new problem
    public ArchiveProblem addProblem(ArchiveProblem archiveProblem) {
        return archiveProblemRepository.save(archiveProblem);
    }

    // Get all problems
    public List<ArchiveProblem> getAllProblems() {
        return archiveProblemRepository.findAll();
    }

    // Get a specific problem by ID
    public Optional<ArchiveProblem> getProblemById(Long id) {
        return archiveProblemRepository.findById(id);
    }
}
