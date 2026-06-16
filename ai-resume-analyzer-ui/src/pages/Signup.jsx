import { useState } from "react";
import api from "../services/api";

function Signup() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] =
    useState("");

  const [loading, setLoading] = useState(false);

  const handleSignup = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    try {
      setLoading(true);

      await api.post("/api/users/signup",
        {
          name,
          email,
          password,
        }
      );

      alert("Account Created Successfully!");

      window.location.href = "/login";
    } catch (error) {
      console.error(error);

      alert(
        error?.response?.data?.message ||
          "Signup Failed"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      className="container-fluid"
      style={{
        minHeight: "100vh",
        background:
          "linear-gradient(135deg,#0f172a,#1e293b,#334155)",
      }}
    >
      <div className="row min-vh-100">

        {/* LEFT SECTION */}

        <div
          className="col-lg-6 d-none d-lg-flex flex-column justify-content-center text-white p-5"
        >
          <h1
            className="display-3 fw-bold"
          >
            🤖 AI Resume Analyzer
          </h1>

          <p
            className="lead mt-4"
            style={{
              maxWidth: "500px",
            }}
          >
            Analyze resumes using AI,
            improve ATS scores,
            and match candidates with jobs instantly.
          </p>

          <div className="mt-5">

            <h5 className="mb-3">
              ✅ ATS Score Analysis
            </h5>

            <h5 className="mb-3">
              ✅ Resume Improvement Suggestions
            </h5>

            <h5 className="mb-3">
              ✅ Job Match Percentage
            </h5>

            <h5 className="mb-3">
              ✅ Missing Skills Detection
            </h5>

            <h5 className="mb-3">
              ✅ AI Powered Insights
            </h5>

          </div>
        </div>

        {/* RIGHT SECTION */}

        <div
          className="col-lg-6 d-flex justify-content-center align-items-center"
        >
          <div
            className="card border-0 shadow-lg"
            style={{
              width: "500px",
              borderRadius: "30px",
              background:
                "rgba(255,255,255,0.95)",
              backdropFilter: "blur(20px)",
            }}
          >
            <div className="card-body p-5">

              <div className="text-center mb-4">

                <div
                  style={{
                    fontSize: "70px",
                  }}
                >
                  🚀
                </div>

                <h2 className="fw-bold">
                  Create Account
                </h2>

                <p className="text-muted">
                  Join AI Resume Analyzer
                </p>

              </div>

              <form onSubmit={handleSignup}>

                <div className="mb-3">
                  <label className="form-label">
                    Full Name
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    value={name}
                    onChange={(e) =>
                      setName(e.target.value)
                    }
                    required
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">
                    Email Address
                  </label>

                  <input
                    type="email"
                    className="form-control"
                    value={email}
                    onChange={(e) =>
                      setEmail(e.target.value)
                    }
                    required
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">
                    Password
                  </label>

                  <input
                    type="password"
                    className="form-control"
                    value={password}
                    onChange={(e) =>
                      setPassword(e.target.value)
                    }
                    required
                  />
                </div>

                <div className="mb-4">
                  <label className="form-label">
                    Confirm Password
                  </label>

                  <input
                    type="password"
                    className="form-control"
                    value={confirmPassword}
                    onChange={(e) =>
                      setConfirmPassword(
                        e.target.value
                      )
                    }
                    required
                  />
                </div>

                <button
                  type="submit"
                  className="btn btn-primary w-100 py-2 fw-bold"
                  disabled={loading}
                >
                  {loading
                    ? "Creating Account..."
                    : "Create Account"}
                </button>

              </form>

              <div className="text-center mt-4">

                <span>
                  Already have an account?
                </span>

                <a
                  href="/login"
                  className="ms-2 fw-bold text-decoration-none"
                >
                  Login
                </a>

              </div>

            </div>
          </div>
        </div>

      </div>
    </div>
  );
}

export default Signup;