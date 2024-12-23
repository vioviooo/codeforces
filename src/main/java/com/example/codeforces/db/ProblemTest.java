package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "problem_tests")
public class ProblemTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private Long testId;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    @JsonBackReference("reference-problemtest-archiveproblem")
    private ArchiveProblem problem;

    @Column(name = "input_data", length = 255)
    private String inputData;

    @Column(name = "expected_output", length = 255)
    private String expectedOutput;

    // Getters and setters
    public ArchiveProblem getProblem() {
        return problem;
    }

    public void setProblem(ArchiveProblem problem) {
        this.problem = problem;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    // Constructors
    public ProblemTest() {}

    public ProblemTest(ArchiveProblem problem, String inputData, String expectedOutput) {
        this.problem = problem;
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
    }
}
