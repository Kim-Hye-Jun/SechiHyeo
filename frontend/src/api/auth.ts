// 로그인, 회원 가입등 인증이 필요없는 경우
import { instance } from "./index";
// import axios from "axios";
// 회원가입 API
function registerUser(member: object) {
  // const params = member;
  console.log(member);
  return instance.post("register/signup", null, { params: member });
}

// 로그인 API
function loginUser(member: object) {
  return instance.post("member/login", null, { params: member });
}

export { registerUser, loginUser };
