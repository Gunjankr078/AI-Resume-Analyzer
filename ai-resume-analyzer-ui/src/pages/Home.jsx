import { useEffect, useState } from "react";
import axios from "axios";

import UploadCard from "../components/UploadCard";
import AnalysisCard from "../components/AnalysisCard";
import HistoryCard from "../components/HistoryCard";

function Home() {
  const [file, setFile] = useState(null);
  const [result, setResult] = useState(null);
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(false);
  const [loadingMessage, setLoadingMessage] = useState("");
  const [jobDescription, setJobDescription] = useState("");
  const [jobMatch, setJobMatch] = useState(null);

  useEffect(() => {
    loadHistory();
  }, []);

  const loadHistory = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/resume/history",
      );

      setHistory(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleUpload = async () => {
    if (!file) {
      alert("Please select a PDF file");
      return;
    }

    const formData = new FormData();

    formData.append("file", file);

    try {
      setLoading(true);

      setLoadingMessage("📄 Analyzing your resume...");

      await new Promise((resolve) => setTimeout(resolve, 1000));

      setLoadingMessage("🎯 Calculating ATS score...");

      await new Promise((resolve) => setTimeout(resolve, 1000));

      setLoadingMessage("🧠 Finding strengths and missing skills...");

      await new Promise((resolve) => setTimeout(resolve, 1000));
      setLoading(true);

      const response = await axios.post(
        "http://localhost:8080/api/resume/analyze",
        formData,
      );

      setResult(response.data);

      loadHistory();
    } catch (error) {
      console.error(error);
      alert("Upload failed");
    } finally {
      setLoading(false);
      setLoadingMessage("");
    }
  };

  const handleJobMatch = async () => {
    if (!file) {
      alert("Please upload a resume");
      return;
    }

    if (!jobDescription.trim()) {
      alert("Please enter a Job Description");
      return;
    }

    const formData = new FormData();

    formData.append("file", file);
    formData.append("jobDescription", jobDescription);

    try {
      setLoading(true);

      setLoadingMessage("📄 Reading resume...");

      await new Promise((resolve) => setTimeout(resolve, 1000));

      setLoadingMessage("🎯 Matching with Job Description...");

      await new Promise((resolve) => setTimeout(resolve, 1000));

      setLoadingMessage("📊 Calculating match score...");

      await new Promise((resolve) => setTimeout(resolve, 1000));

      setLoadingMessage("🔍 Finding missing keywords...");

      await new Promise((resolve) => setTimeout(resolve, 1000));

      setLoadingMessage("💡 Generating recommendations...");

      const response = await axios.post(
        "http://localhost:8080/api/resume/match",
        formData,
      );

      setJobMatch(response.data);
    } catch (error) {
      console.error(error);
      alert("Matching failed");
    } finally {
      setLoading(false);
      setLoadingMessage("");
    }
  };

  const scoreColor =
    jobMatch?.matchScore >= 75
      ? "bg-success"
      : jobMatch?.matchScore >= 50
        ? "bg-warning"
        : "bg-danger";

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "linear-gradient(135deg,#0f172a,#1e293b,#334155)",
      }}
    >
      <div className="container py-5">
        {loading && (
          <div
            className="card shadow-lg border-0 mx-auto mb-4"
            style={{
              maxWidth: "900px",
              borderRadius: "20px",
            }}
          >
            <div className="card-body text-center p-4">
              <div
                className="spinner-border text-primary mb-3"
                role="status"
              ></div>

              <h5>{loadingMessage}</h5>
            </div>
          </div>
        )}
        <div className="text-center text-white mb-5">
          <h1 className="fw-bold">AI Resume Analyzer</h1>

          <p>Analyze your resume using AI and get ATS insights</p>
        </div>

        <UploadCard
          file={file}
          setFile={setFile}
          handleUpload={handleUpload}
          loading={loading}
        />

        {/* JOB DESCRIPTION CARD */}

        <div
          className="card shadow-lg border-0 mx-auto mt-4"
          style={{
            maxWidth: "900px",
            borderRadius: "20px",
          }}
        >
          <div className="card-body p-4">
            <h3 className="mb-3">🎯 Job Description Matching</h3>

            <textarea
              className="form-control"
              rows="8"
              placeholder="Paste Job Description Here..."
              value={jobDescription}
              onChange={(e) => setJobDescription(e.target.value)}
            />

            <button
              className="btn btn-success mt-3 w-100"
              onClick={handleJobMatch}
            >
              Match Resume
            </button>
          </div>
        </div>

        <AnalysisCard result={result} />

        {jobMatch && (
          <div
            className="card shadow-lg border-0 mx-auto mt-4"
            style={{
              maxWidth: "900px",
              borderRadius: "20px",
            }}
          >
            <div className="card-body p-4">
              <h3 className="text-center mb-4">🎯 ATS Match Result</h3>

              <p className="text-center text-muted mb-2">
                Resume: {file?.name}
              </p>

              <h2 className="text-center">
                Match Score: {jobMatch.matchScore}%
              </h2>

              <div className="progress mt-3 mb-4" style={{ height: "30px" }}>
                <div
                  className={`progress-bar ${scoreColor}`}
                  style={{
                    width: `${jobMatch.matchScore}%`,
                  }}
                >
                  {jobMatch.matchScore}%
                </div>
              </div>

              <h4>❌ Missing Keywords</h4>

              <div className="mb-4">
                {jobMatch.missingKeywords?.map((skill, index) => (
                  <span key={index} className="badge bg-danger me-2 mb-2 p-2">
                    {skill}
                  </span>
                ))}
              </div>

              <h4>💡 Recommendations</h4>

              <ul className="list-group">
                {jobMatch.recommendations?.map((item, index) => (
                  <li key={index} className="list-group-item">
                    {item}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        )}

        {console.log(history)}
        <HistoryCard history={history} />
      </div>
    </div>
  );
}

export default Home;
