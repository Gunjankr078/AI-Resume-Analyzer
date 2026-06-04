package com.gunjan.airesumeanalyzer.controller;

// import java.io.IOException;
import java.util.List;

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

        ResumeAnalysisResponse analysis = ollamaService.analyzeResume(
                resumeText);

        return analysis;
    }

    @GetMapping("/history")
    public List<ResumeAnalysis> getHistory() {
        return resumeAnalysisService.getAllAnalyses();
    }
}