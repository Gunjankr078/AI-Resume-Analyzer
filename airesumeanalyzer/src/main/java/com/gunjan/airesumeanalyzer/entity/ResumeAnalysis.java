package com.gunjan.airesumeanalyzer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "resume_analysis")
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String analysis;

    private LocalDateTime createdAt;

    public ResumeAnalysis() {
    }

    public ResumeAnalysis(
            String fileName,
            String analysis,
            LocalDateTime createdAt) {

        this.fileName = fileName;
        this.analysis = analysis;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAnalysis() {
        return analysis;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}