package com.example.codeforces.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "contest_problems")
public class ContestProblem {
    @EmbeddedId
    private ContestProblemId id;

    @ManyToOne
    @MapsId("contestId")
    @JsonBackReference("reference-contest-contestproblem")
    private Contest contest;

    @ManyToOne
    @MapsId("problemId")
    @JsonBackReference("reference-contest-archiveproblem")
    private ArchiveProblem problem;

    // Getters, Setters, Constructors
}
