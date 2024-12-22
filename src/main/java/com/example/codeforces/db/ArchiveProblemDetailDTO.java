package com.example.codeforces.db;

import com.example.codeforces.db.ProblemTest;

import java.util.List;
import java.util.stream.Collectors;

public class ArchiveProblemDetailDTO {
    private Long problemId;
    private String title;
    private String difficulty;
    private String statement;
    private Integer timeLimit;
    private Integer memoryLimit;
    private List<ProblemTestDTO> problemTests;

    public ArchiveProblemDetailDTO(Long problemId, String title, String difficulty, String statement,
                                   Integer timeLimit, Integer memoryLimit, List<ProblemTestDTO> problemTests) {
        this.problemId = problemId;
        this.title = title;
        this.difficulty = difficulty;
        this.statement = statement;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.problemTests = problemTests;
    }

    public static class ProblemTestDTO {
        private String inputData;
        private String expectedOutput;

        public ProblemTestDTO(String inputData, String expectedOutput) {
            this.inputData = inputData;
            this.expectedOutput = expectedOutput;
        }

        public String getInputData() {
            return inputData;
        }

        public String getExpectedOutput() {
            return expectedOutput;
        }
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getStatement() {
        return statement;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public List<ProblemTestDTO> getProblemTests() {
        return problemTests;
    }

    public static ArchiveProblemDetailDTO fromEntity(ArchiveProblem problem) {
        List<ProblemTestDTO> testDTOs = problem.getProblemTests().stream()
                .map(test -> new ProblemTestDTO(test.getInputData(), test.getExpectedOutput()))
                .collect(Collectors.toList());

        return new ArchiveProblemDetailDTO(
                problem.getProblemId(),
                problem.getTitle(),
                problem.getDifficulty(),
                problem.getStatement(),
                problem.getTimeLimit(),
                problem.getMemoryLimit(),
                testDTOs
        );
    }
}
