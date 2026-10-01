import { useState } from "react";
import api from "../services/api";
import { motion } from "framer-motion";
import { FaUserCircle } from "react-icons/fa";
import "../assets/login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showIntro, setShowIntro] = useState(false);

  const handleLogin = async () => {
    try {
      const response = await api.post("/api/users/login", {
        email,
        password,
      });

      localStorage.setItem("userId", response.data.userId);
      localStorage.setItem("userName", response.data.name);

      setShowIntro(true);
    } catch (err) {
      alert(err.response?.data || "Login Failed");
    }
  };

  return (
    <>
      {showIntro && (
        <div className="welcome-intro">
          <video
            autoPlay
            playsInline
            onEnded={() => {
              window.location.href = "/dashboard";
            }}
          >
            <source src="/welcome-intro.mp4" type="video/mp4" />
          </video>

          <button
            className="skip-intro-btn"
            onClick={() => {
              window.location.href = "/dashboard";
            }}
          >
            Skip Welcome Video
          </button>
        </div>
      )}
      <div className="login-page">
        <video
          className="login-background-video"
          autoPlay
          loop
          muted
          playsInline
          aria-hidden="true"
        >
          <source src="/ai-background.mp4" type="video/mp4" />
        </video>

        <div className="login-video-overlay"></div>
        <motion.div
          initial={{ opacity: 0, y: 40 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.7 }}
          className="login-card"
        >
          <FaUserCircle size={80} color="#60a5fa" className="mb-3" />

          <h1>Welcome Back</h1>

          <p>Login to access your AI Resume Dashboard</p>

          <input
            type="email"
            placeholder="Email Address"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button onClick={handleLogin}>Login</button>

          <div className="text-center mt-4">
            <span>Don't have an account?</span>

            <a href="/signup" className="ms-2 fw-bold text-decoration-none">
              Sign Up
            </a>
          </div>
        </motion.div>
      </div>
    </>
  );
}

export default Login;
