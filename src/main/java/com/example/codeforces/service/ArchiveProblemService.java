package com.example.codeforces.service;

import com.example.codeforces.db.ArchiveProblem;
import com.example.codeforces.db.ArchiveProblemDetailDTO;
import com.example.codeforces.db.ProblemTest;
import com.example.codeforces.repository.ArchiveProblemRepository;
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

    public boolean addProblemTest(Long problemId, ProblemTest problemTest) {
        Optional<ArchiveProblem> problemOptional = archiveProblemRepository.findById(problemId);
        if (problemOptional.isPresent()) {
            ArchiveProblem problem = problemOptional.get();
            problem.getProblemTests().add(problemTest);
            archiveProblemRepository.save(problem);
            return true;
        }
        return false;
    }

    // Get all problems
    public List<ArchiveProblem> getAllProblems() {
        return archiveProblemRepository.findAll();
    }

    // Get a specific problem by ID
    public Optional<ArchiveProblem> getProblemById(Long id) {
        return archiveProblemRepository.findById(id);
    }

    // Get a detailed description as DTO
    public Optional<ArchiveProblemDetailDTO> getDetailedProblemById(Long id) {
        return archiveProblemRepository.findById(id)
                .map(ArchiveProblemDetailDTO::fromEntity);
    }

    // Delete a contest
    public boolean deleteArchiveProblem(Long id) {
        if (archiveProblemRepository.existsById(id)) {
            archiveProblemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
