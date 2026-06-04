package com.gunjan.airesumeanalyzer.ai;

import com.gunjan.airesumeanalyzer.dto.JobMatchResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gunjan.airesumeanalyzer.dto.ResumeAnalysisResponse;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {

        private final RestTemplate restTemplate = new RestTemplate();

        public ResumeAnalysisResponse analyzeResume(String resumeText) throws Exception {

                String prompt = """
                                You are an expert technical recruiter specializing in modern engineering talent. Analyze the following resume thoroughly.

                                [START OF RESUME]
                                """
                                + resumeText
                                + """
                                                [END OF RESUME]

                                                Based on the resume above, evaluate the candidate and generate an assessment.

                                                CONTENT EVALUATION RULES:
                                                1. "missingSkills" must contain high-demand, modern technical skills that are completely absent from the candidate's resume.
                                                2. Cross-reference the resume against the following high-priority, trending market skills and fill "missingSkills" using relevant gaps from this list:
                                                   - Artificial Intelligence: Generative AI, Agentic LLM frameworks (LangChain, AutoGen), Model Context Protocol (MCP), Vector Databases (Pinecone, Milvus), Prompt Engineering.
                                                   - Cloud & DevOps: AWS/Azure Architecture, Terraform, Docker, Kubernetes, CI/CD automation, SRE practices.
                                                   - Frameworks & Languages: TypeScript, Go (Golang), Rust, Next.js, FastAPI, Spring Boot 3.x.
                                                   - Data & Security: Advanced SQL, Data Pipelines, Cloud Security, Threat Modeling.
                                                3. If a skill is recommended in the suggestions array, it must also appear in missingSkills.
                                                4. "suggestions" must be highly actionable career or technical design improvements.
                                                5. Do not leave missingSkills empty unless the resume already explicitly features multiple cutting-edge tools from the trending market list above.

                                                REQUIRED JSON FORMAT:
                                                {
                                                  "score": <integer between 1 and 100>,
                                                  "strengths": ["string", "string"],
                                                  "missingSkills": ["string", "string"],
                                                  "suggestions": ["string", "string"]
                                                }

                                                CRITICAL RULES FOR OUTPUT:
                                                1. Return ONLY valid JSON.
                                                2. Do NOT wrap the response in markdown code blocks (do not use ```json ... ```).
                                                3. Do NOT include any text before or after the JSON.
                                                4. The score must be an integer between 1 and 100.
                                                5. "strengths" must contain only plain text strings.
                                                6. "missingSkills" must contain only plain text strings.
                                                7. "suggestions" must contain only plain text strings.
                                                8. Do NOT place JSON objects inside arrays.
                                                """;

                Map<String, Object> request = Map.of(
                                "model", "llama3.2:3b",
                                "prompt", prompt,
                                "stream", false);

                String url = "http://localhost:11434/api/generate";

                Map<?, ?> response = restTemplate.postForObject(
                                url,
                                request,
                                Map.class);

                String jsonResponse = response.get("response").toString();

                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(
                                jsonResponse,
                                ResumeAnalysisResponse.class);
        }

        public JobMatchResponse matchResumeWithJob(
                        String resumeText,
                        String jobDescription) throws Exception {

                String prompt = """
                                                                You are an ATS Resume Matching System.

                                                                Compare the resume and job description.

                                                                Calculate:
                                                                1. Match score from 0-100.
                                                                2. Missing keywords from the job description that do not appear in the resume.
                                                                3. Recommendations to improve the resume.

                                                                Return ONLY valid JSON:

                                                                {
                                                                  "matchScore": 0,
                                                                  "missingKeywords": [],
                                                                  "recommendations": []
                                                                }

                                Rules:
                                - matchScore must be calculated based on skill overlap.
                                - If 50% of required skills are present, score should be around 50.
                                - If 70% of required skills are present, score should be around 70.
                                - Do not return 0 unless none of the required skills match.
                                - Do not return 100 unless almost all required skills match.
                                - Do not return 0 unless the resume is completely unrelated.
                                - Compare skills, frameworks, tools, cloud platforms, databases, and technologies.
                                - missingKeywords should contain only missing skills.
                                - recommendations should explain how to improve the match.
                                - Return ONLY JSON.

                                                                Resume:
                                                                """
                                + resumeText +

                                """

                                                Job Description:
                                                """ + jobDescription;

                Map<String, Object> request = Map.of(
                                "model", "llama3.2:3b",
                                "prompt", prompt,
                                "stream", false);

                String url = "http://localhost:11434/api/generate";

                Map<?, ?> response = restTemplate.postForObject(
                                url,
                                request,
                                Map.class);

                String json = response.get("response").toString();

                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(
                                json,
                                JobMatchResponse.class);
        }
}