import axios from "axios";

const api = axios.create({
  baseURL: "https://ai-resume-analyzer-production-0da6.up.railway.app"
});

export default api;