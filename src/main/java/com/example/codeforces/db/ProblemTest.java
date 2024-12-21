//package com.example.codeforces.db;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*; // Импорты для JPA
//import jakarta.persistence.Id; // ПРАВИЛЬНЫЙ ИМПОРТ
//
//@Entity
//@Table(name = "problem_tests")
//public class ProblemTest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long testId;
//
//    @ManyToOne
//    @MapsId("problemId") // Maps the problemId part of the composite key
//    @JoinColumn(name = "problem_id", nullable = false)
//    @JsonBackReference
//    private ArchiveProblem problem;
//
//    private String inputData;
//    private String expectedOutput;
//
//    // Getters
//    public Long getTestId() {
//        return testId;
//    }
//
//    public ArchiveProblem getProblem() {
//        return problem;
//    }
//
//    public String getInputData() {
//        return inputData;
//    }
//
//    public String getExpectedOutput() {
//        return expectedOutput;
//    }
//
//    // Setters
//    public void setTestId(Long testId) {
//        this.testId = testId;
//    }
//
//    public void setProblem(ArchiveProblem problem) {
//        this.problem = problem;
//    }
//
//    public void setInputData(String inputData) {
//        this.inputData = inputData;
//    }
//
//    public void setExpectedOutput(String expectedOutput) {
//        this.expectedOutput = expectedOutput;
//    }
//
//    // Конструкторы
//    public ProblemTest() {}
//
//    public ProblemTest(ArchiveProblem problem, String inputData, String expectedOutput) {
//        this.problem = problem;
//        this.inputData = inputData;
//        this.expectedOutput = expectedOutput;
//    }
//}
package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "problem_tests")
public class ProblemTest {

    @EmbeddedId
    private ProblemTestKey id;

    @ManyToOne
    @MapsId("problemId") // Maps the problemId in the composite key
    @JoinColumn(name = "problem_id", nullable = false)
    @JsonBackReference
    private ArchiveProblem problem;

    private String inputData;
    private String expectedOutput;

    // Getters and setters
    public ProblemTestKey getId() {
        return id;
    }

    public void setId(ProblemTestKey id) {
        this.id = id;
    }

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
