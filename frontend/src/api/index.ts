import axios from "axios";
import { API_BASE_URL } from "@/config";
import { setInterceptors } from "./common/interceptors";

// JWT 인증이 따로 필요없는 axios
function createInstance() {
  return axios.create({
    baseURL: API_BASE_URL,
  });
}

// JWT 인증이 따로 필요한 axios
function createInstanceWithAuth(url: string) {
  const instance = axios.create({
    baseURL: `${API_BASE_URL}${url}`,
  });
  return setInterceptors(instance); // JWT를
}

function createInstanceWithAuth2() {
  const instance = axios.create({
    baseURL: `${API_BASE_URL}`,
  });
  return setInterceptors(instance); // JWT를
}

export const instance = createInstance();
export const member = createInstanceWithAuth("member");
export const member2 = createInstanceWithAuth2();
export const debateApply = createInstanceWithAuth("debate-apply");
export const board = createInstanceWithAuth("debate-board");
export const reply = createInstanceWithAuth("debate-board");
