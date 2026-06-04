import axios from "axios";

import { FaRobot } from "react-icons/fa";

function Navbar() {
  return (
    <nav className="navbar navbar-dark bg-dark shadow">
      <div className="container">
        <span className="navbar-brand fw-bold">
          <FaRobot className="me-2" />
          AI Resume Analyzer
        </span>
      </div>
    </nav>
  );
}

export default Navbar;