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

  useEffect(() => {
    loadHistory();
  }, []);

  const loadHistory = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/resume/history"
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

      const response = await axios.post(
        "http://localhost:8080/api/resume/analyze",
        formData
      );

      setResult(response.data);

      loadHistory();

    } catch (error) {

      console.error(error);
      alert("Upload failed");

    } finally {

      setLoading(false);

    }

  };

  return (
    <div
      style={{
        minHeight: "100vh",
        background:
          "linear-gradient(135deg,#0f172a,#1e293b,#334155)",
      }}
    >
      <div className="container py-5">

        <div className="text-center text-white mb-5">

          <h1 className="fw-bold">
            AI Resume Analyzer
          </h1>

          <p>
            Analyze your resume using AI and get ATS insights
          </p>

        </div>

        <UploadCard
          file={file}
          setFile={setFile}
          handleUpload={handleUpload}
          loading={loading}
        />

        <AnalysisCard
          result={result}
        />

        <HistoryCard
          history={history}
        />

      </div>
    </div>
  );
}

export default Home;