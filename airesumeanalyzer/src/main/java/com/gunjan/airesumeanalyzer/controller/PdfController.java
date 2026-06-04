package com.gunjan.airesumeanalyzer.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gunjan.airesumeanalyzer.service.PdfService;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/extract")
    public String extractText(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return pdfService.extractText(file);
    }
}