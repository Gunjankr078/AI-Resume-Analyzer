function AnalysisCard({ result }) {
  if (!result) return null;

  return (
    <div
      className="card shadow-lg border-0 mx-auto mt-4"
      style={{
        maxWidth: "1200px",
        borderRadius: "20px",
      }}
    >
      <div className="card-body p-4">
        <h3 className="text-center mb-4">AI Analysis Result</h3>

        <h2 className="text-center mb-4">ATS Score: {result.score}%</h2>

        <div className="progress mb-4" style={{ height: "30px" }}>
          <div
            className="progress-bar"
            role="progressbar"
            style={{
              width: `${result.score}%`,
            }}
          >
            {result.score}%
          </div>
        </div>

        <h4>✅ Strengths</h4>

        <div className="mb-4">
          {result.strengths?.map((skill, index) => (
            <div key={index} className="alert alert-success">
              {skill}
            </div>
          ))}
        </div>

        <h4>❌ Missing Skills</h4>

        <div className="mb-4">
          {result.missingSkills?.length > 0 ? (
            result.missingSkills?.map((skill, index) => (
              <div key={index} className="alert alert-danger">
                {skill}
              </div>
            ))
          ) : (
            <p className="text-muted">No major missing skills identified</p>
          )}
        </div>

        <h4>💡 Suggestions</h4>

        <div
          style={{
            maxHeight: "250px",
            overflowY: "auto",
          }}
        >
          <ul className="list-group">
            {result.suggestions?.map((item, index) => (
              <li key={index} className="list-group-item">
                {item}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default AnalysisCard;
