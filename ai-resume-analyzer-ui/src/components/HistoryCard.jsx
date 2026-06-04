function HistoryCard({ history }) {
  if (!history?.length) return null;

  return (
    <div
      className="card shadow-lg border-0 mx-auto mt-4"
      style={{
        maxWidth: "900px",
        borderRadius: "20px",
      }}
    >
      <div className="card-body">
        <h3 className="mb-4">📜 Analysis History</h3>

        <table className="table">
          <thead>
            <tr>
              <th>File Name</th>
              <th>Date</th>
            </tr>
          </thead>

          <tbody>
            {history.map((item) => (
              <tr key={item.id}>
                <td>{item.fileName}</td>
                <td>
                  {new Date(item.createdAt).toLocaleString("en-IN", {
                    day: "2-digit",
                    month: "short",
                    year: "numeric",
                    hour: "2-digit",
                    minute: "2-digit",
                  })}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default HistoryCard;
