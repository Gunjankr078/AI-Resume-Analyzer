package com.gunjan.airesumeanalyzer.controller;

// import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gunjan.airesumeanalyzer.dto.JobMatchResponse;

import org.springframework.web.bind.annotation.*;
import com.gunjan.airesumeanalyzer.dto.ResumeAnalysisResponse;

import org.springframework.web.multipart.MultipartFile;

import com.gunjan.airesumeanalyzer.ai.OllamaService;
import com.gunjan.airesumeanalyzer.entity.ResumeAnalysis;
import com.gunjan.airesumeanalyzer.service.PdfService;
import com.gunjan.airesumeanalyzer.service.ResumeAnalysisService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final PdfService pdfService;
    private final OllamaService ollamaService;
    private final ResumeAnalysisService resumeAnalysisService;

    public ResumeController(
            PdfService pdfService,
            OllamaService ollamaService,
            ResumeAnalysisService resumeAnalysisService) {

        this.pdfService = pdfService;
        this.ollamaService = ollamaService;
        this.resumeAnalysisService = resumeAnalysisService;
    }

    @PostMapping("/analyze")
    public ResumeAnalysisResponse analyzeResume(
            @RequestParam("file") MultipartFile file)
            throws Exception {

        String resumeText = pdfService.extractText(file);

        ResumeAnalysisResponse analysis = ollamaService.analyzeResume(resumeText);

        ObjectMapper mapper = new ObjectMapper();

        String analysisJson = mapper.writeValueAsString(analysis);

        resumeAnalysisService.saveAnalysis(
                file.getOriginalFilename(),
                analysisJson);

        return analysis;
    }

    @PostMapping("/match")
    public JobMatchResponse matchResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription)
            throws Exception {

        String resumeText = pdfService.extractText(file);

        return ollamaService.matchResumeWithJob(
                resumeText,
                jobDescription);
    }

    @GetMapping("/history")
    public List<ResumeAnalysis> getHistory() {
        return resumeAnalysisService.getAllAnalyses();
    }
}