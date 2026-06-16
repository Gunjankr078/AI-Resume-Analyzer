package com.gunjan.airesumeanalyzer.controller;

// import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gunjan.airesumeanalyzer.dto.JobMatchResponse;

import org.springframework.web.bind.annotation.*;
import com.gunjan.airesumeanalyzer.dto.ResumeAnalysisResponse;

import org.springframework.web.multipart.MultipartFile;

import com.gunjan.airesumeanalyzer.ai.GeminiService;
import com.gunjan.airesumeanalyzer.ai.OllamaService;
import com.gunjan.airesumeanalyzer.entity.ResumeAnalysis;
import com.gunjan.airesumeanalyzer.service.PdfService;
import com.gunjan.airesumeanalyzer.service.ResumeAnalysisService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final PdfService pdfService;
    private final GeminiService geminiService;
    private final ResumeAnalysisService resumeAnalysisService;

    public ResumeController(
        PdfService pdfService,
        GeminiService geminiService,
        ResumeAnalysisService resumeAnalysisService) {

        this.pdfService = pdfService;
        this.geminiService = geminiService;
        this.resumeAnalysisService = resumeAnalysisService;
    }

@PostMapping("/analyze")
public ResumeAnalysisResponse analyzeResume(
        @RequestParam("file") MultipartFile file,
        @RequestParam("userId") Long userId)
        throws Exception {

    System.out.println("==========");
    System.out.println("USER ID = " + userId);
    System.out.println("FILE = " + file.getOriginalFilename());
    System.out.println("==========");

    String resumeText = pdfService.extractText(file);

    ResumeAnalysisResponse analysis =
        geminiService.analyzeResume(resumeText);

    ObjectMapper mapper = new ObjectMapper();

    String analysisJson =
            mapper.writeValueAsString(analysis);

    resumeAnalysisService.saveAnalysis(
            file.getOriginalFilename(),
            analysisJson,
            userId);

    return analysis;
}

    @PostMapping("/match")
    public JobMatchResponse matchResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription)
            throws Exception {

        String resumeText = pdfService.extractText(file);

        return geminiService.matchResumeWithJob(
        resumeText,
        jobDescription);
    }

    @GetMapping("/history/{userId}")
public List<ResumeAnalysis> getHistory(
        @PathVariable Long userId) {

    return resumeAnalysisService
            .getUserAnalyses(userId);
}
}