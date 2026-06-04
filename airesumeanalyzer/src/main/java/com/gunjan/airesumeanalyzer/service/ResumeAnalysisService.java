package com.gunjan.airesumeanalyzer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gunjan.airesumeanalyzer.entity.ResumeAnalysis;
import com.gunjan.airesumeanalyzer.repository.ResumeAnalysisRepository;

@Service
public class ResumeAnalysisService {

    private final ResumeAnalysisRepository repository;

    public ResumeAnalysisService(
            ResumeAnalysisRepository repository) {

        this.repository = repository;
    }

    public void saveAnalysis(
            String fileName,
            String analysis) {

        ResumeAnalysis resumeAnalysis =
                new ResumeAnalysis(
                        fileName,
                        analysis,
                        LocalDateTime.now());

        repository.save(resumeAnalysis);
    }

    public List<ResumeAnalysis> getAllAnalyses() {
        return repository.findAll();
    }
}