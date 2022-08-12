import axios from "axios";

const BASE_URL = "https://localhost:9999/api";

export default axios.create({
  baseURL: `${BASE_URL}`,
});
