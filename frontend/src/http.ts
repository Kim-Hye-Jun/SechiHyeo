import axios from "axios";

const BASE_URL = "http://i7a508.p.ssafy.io:8080/api/";
// const BASE_URL = "http://localhost:8081/api/";

export default axios.create({
  baseURL: `${BASE_URL}`,
});
