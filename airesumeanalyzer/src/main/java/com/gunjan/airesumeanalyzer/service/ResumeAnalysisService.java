package com.gunjan.airesumeanalyzer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gunjan.airesumeanalyzer.entity.ResumeAnalysis;
import com.gunjan.airesumeanalyzer.entity.User;
import com.gunjan.airesumeanalyzer.repository.ResumeAnalysisRepository;
import com.gunjan.airesumeanalyzer.repository.UserRepository;

@Service
public class ResumeAnalysisService {

    private final ResumeAnalysisRepository repository;
    private final UserRepository userRepository;

    public ResumeAnalysisService(
            ResumeAnalysisRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

public void saveAnalysis(
        String fileName,
        String analysis,
        Long userId) {

    System.out.println("Saving analysis...");
    System.out.println("Received userId = " + userId);

    User user = userRepository.findById(userId)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    System.out.println("Found user = " + user.getEmail());

    ResumeAnalysis resumeAnalysis =
            new ResumeAnalysis();

    resumeAnalysis.setFileName(fileName);
    resumeAnalysis.setAnalysis(analysis);
    resumeAnalysis.setCreatedAt(LocalDateTime.now());
    resumeAnalysis.setUser(user);

    repository.save(resumeAnalysis);

    System.out.println("Saved successfully!");
}

    public List<ResumeAnalysis> getUserAnalyses(
            Long userId) {

        return repository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    public ResumeAnalysis getAnalysisById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analysis not found"));
    }

    public void deleteAnalysis(Long id) {
        repository.deleteById(id);
    }
}