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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ResumeAnalysis() {
    }

    public ResumeAnalysis(
            String fileName,
            String analysis,
            LocalDateTime createdAt,
            User user) {

        this.fileName = fileName;
        this.analysis = analysis;
        this.createdAt = createdAt;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}