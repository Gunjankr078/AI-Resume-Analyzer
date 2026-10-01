import { FaFileUpload } from "react-icons/fa";

function UploadCard({ file, setFile, handleUpload, loading }) {
  return (
    <div className="upload-card">
      <div className="upload-card-body">
        <h2 className="upload-title">Upload Resume</h2>

        <p className="upload-subtitle">
          Upload your PDF resume and let AI analyze it
        </p>

        <label className="upload-dropzone">
          <FaFileUpload className="dropzone-icon" />

          <span className="dropzone-title">
            {file ? file.name : "Choose your resume"}
          </span>

          <span className="dropzone-text">
            PDF files only • Maximum size 10MB
          </span>

          <input
            type="file"
            accept=".pdf"
            onChange={(e) => setFile(e.target.files[0])}
          />
        </label>

        <button
          className="btn btn-primary upload-button"
          onClick={handleUpload}
          disabled={loading}
        >
          {loading ? "Analyzing..." : "Analyze Resume"}
        </button>

      </div>
    </div>
  );
}

export default UploadCard;