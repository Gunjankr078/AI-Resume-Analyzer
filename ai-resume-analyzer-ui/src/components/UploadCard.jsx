import { FaFileUpload } from "react-icons/fa";

function UploadCard({
  file,
  setFile,
  handleUpload,
  loading,
}) {
  return (
    <div
      className="card shadow-lg border-0 mx-auto"
      style={{
        maxWidth: "700px",
        borderRadius: "20px",
      }}
    >
      <div className="card-body p-5">

        <div className="text-center mb-4">
          <FaFileUpload
            size={50}
            className="text-primary"
          />
        </div>

        <h3 className="text-center mb-4">
          Upload Resume
        </h3>

        <input
          type="file"
          className="form-control mb-4"
          accept=".pdf"
          onChange={(e) =>
            setFile(e.target.files[0])
          }
        />

        <button
          className="btn btn-primary w-100 py-3"
          onClick={handleUpload}
        >
          {
            loading
              ? "Analyzing..."
              : "Analyze Resume"
          }
        </button>

      </div>
    </div>
  );
}

export default UploadCard;