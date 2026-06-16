import { FaRobot, FaUserCircle, FaSignOutAlt } from "react-icons/fa";

function Navbar() {
  const userName = localStorage.getItem("userName");

  const handleLogout = () => {
    localStorage.removeItem("userId");
    localStorage.removeItem("userName");

    window.location.href = "/login";
  };

  return (
    <nav
      className="navbar navbar-expand-lg shadow"
      style={{
        background: "linear-gradient(135deg, #0f172a, #1e293b)",
        padding: "14px 0",
      }}
    >
      <div className="container">
        <span
          className="navbar-brand fw-bold text-white"
          style={{
            fontSize: "1.4rem",
          }}
        >
          <FaRobot className="me-2" />
          AI Resume Analyzer
        </span>

        <div className="d-flex align-items-center">
          {userName && (
            <>
              <div
                className="d-flex align-items-center me-4"
                style={{
                  color: "white",
                }}
              >
                <FaUserCircle size={30} className="me-2" />

                <span>
                  Welcome, <strong>{userName}</strong>
                </span>
              </div>

              <button className="btn btn-outline-light" onClick={handleLogout}>
                <FaSignOutAlt className="me-2" />
                Logout
              </button>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
