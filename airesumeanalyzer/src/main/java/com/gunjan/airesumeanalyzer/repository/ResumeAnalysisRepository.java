package com.gunjan.airesumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunjan.airesumeanalyzer.entity.ResumeAnalysis;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {

}