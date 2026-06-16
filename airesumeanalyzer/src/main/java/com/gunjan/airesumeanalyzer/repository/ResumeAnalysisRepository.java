package com.gunjan.airesumeanalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunjan.airesumeanalyzer.entity.ResumeAnalysis;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {

    List<ResumeAnalysis> findByUserIdOrderByCreatedAtDesc(Long userId);
}