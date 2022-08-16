import axios from "axios";

const BASE_URL = "https://i7a508.p.ssafy.io/api/";
// const BASE_URL = "http://localhost:8081/api/";

export default axios.create({
  baseURL: `${BASE_URL}`,
});
