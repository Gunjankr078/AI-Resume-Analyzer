package com.gunjan.airesumeanalyzer.ai;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gunjan.airesumeanalyzer.dto.ResumeAnalysisResponse;
import com.gunjan.airesumeanalyzer.dto.JobMatchResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

        @Value("${gemini.api.key}")
        private String apiKey;

        private final RestTemplate restTemplate = new RestTemplate();

        public String generateContent(String prompt) {

                try {

                        System.out.println("Using Gemini 2.5 Flash");

                        return callGemini(
                                        prompt,
                                        "gemini-2.5-flash");

                } catch (Exception e) {

                        System.out.println(
                                        "2.5 Flash failed. Switching to 1.5 Flash");

                        try {

                                return callGemini(
                                                prompt,
                                                "gemini-1.5-flash");

                        } catch (Exception ex) {

                                throw new RuntimeException(
                                                "All Gemini models unavailable");
                        }
                }
        }

        private String callGemini(
                        String prompt,
                        String model) {

                String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                                + model
                                + ":generateContent?key="
                                + apiKey;

                Map<String, Object> body = Map.of(
                                "contents",
                                java.util.List.of(
                                                Map.of(
                                                                "parts",
                                                                java.util.List.of(
                                                                                Map.of("text", prompt)))));

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

                ResponseEntity<Map> response = restTemplate.exchange(
                                url,
                                HttpMethod.POST,
                                request,
                                Map.class);

                Map<String, Object> candidate = (Map<String, Object>) ((java.util.List<?>) response.getBody()
                                .get("candidates"))
                                .get(0);

                Map<String, Object> content = (Map<String, Object>) candidate.get("content");

                java.util.List<?> parts = (java.util.List<?>) content.get("parts");

                Map<String, Object> part = (Map<String, Object>) parts.get(0);

                return part.get("text").toString();
        }

        public ResumeAnalysisResponse analyzeResume(
                        String resumeText) throws Exception {

                String prompt = """
                                Analyze this resume.

                                Return ONLY valid JSON.

                                {
                                  "score": 0,
                                  "strengths": [],
                                  "missingSkills": [],
                                  "suggestions": []
                                }

                                Resume:
                                """
                                + resumeText;

                String response = generateContent(prompt);

                response = response
                                .replace("```json", "")
                                .replace("```", "")
                                .trim();

                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(
                                response,
                                ResumeAnalysisResponse.class);
        }

        public JobMatchResponse matchResumeWithJob(
                        String resumeText,
                        String jobDescription) throws Exception {

                String prompt = """
                                Compare this resume with the job description.

                                Return ONLY valid JSON.

                                {
                                  "matchScore": 0,
                                  "missingKeywords": [],
                                  "recommendations": []
                                }

                                Resume:
                                """
                                + resumeText
                                + """

                                                Job Description:
                                                """
                                + jobDescription;

                String response = generateContent(prompt);

                response = response
                                .replace("```json", "")
                                .replace("```", "")
                                .trim();

                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(
                                response,
                                JobMatchResponse.class);
        }
}