package com.gunjan.airesumeanalyzer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gunjan.airesumeanalyzer.dto.ResumeAnalysisResponse;

import com.gunjan.airesumeanalyzer.ai.OllamaService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final OllamaService ollamaService;

    public AIController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @GetMapping("/test")
public ResumeAnalysisResponse testAI() throws Exception {

    String resumeText = """
            Java
            Spring Boot
            MySQL
            React
            """;

    return ollamaService.analyzeResume(resumeText);
}
}