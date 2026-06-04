# AI Resume Analyzer & ATS Matcher

An AI-powered Resume Analyzer and ATS Matcher built using React, Spring Boot, MySQL, and Ollama.

## Features

* Upload PDF resumes
* Extract resume text automatically
* AI-powered resume analysis
* ATS score generation
* Strengths and skill-gap detection
* Personalized improvement suggestions
* Resume-to-Job Description matching
* Match score calculation
* Missing keyword detection
* Analysis history tracking
* Responsive React dashboard

## Tech Stack

### Frontend

* React.js
* Bootstrap
* Axios

### Backend

* Spring Boot
* Java 21
* REST APIs

### Database

* MySQL

### AI

* Ollama
* Llama 3.2 (3B)

## Project Architecture

Resume PDF
↓
Spring Boot Backend
↓
PDF Text Extraction
↓
Ollama (Llama 3.2)
↓
ATS Analysis / Job Matching
↓
MySQL Storage
↓
React Dashboard


## Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/AI-Resume-Analyzer.git
```

### Backend

```bash
cd airesumeanalyzer
./mvnw spring-boot:run
```

### Frontend

```bash
cd ai-resume-analyzer-ui
npm install
npm run dev
```

### Ollama

Install Ollama and pull the model:

```bash
ollama pull llama3.2:3b
```

## Future Enhancements

* PDF report generation
* Authentication & Authorization
* Cloud deployment
* Resume optimization suggestions
* Advanced ATS keyword analytics

## Author

Gunjan
