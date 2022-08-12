// 로그인, 회원 가입등 JWT 토큰 인증이 필요없는 경우
import { instance } from "./index";
// import axios from "axios";

// 회원가입 관련 API
// 회원가입
function registerUser(member: object) {
  // const params = member;
  console.log(member);
  return instance.post("register/signup", null, { params: member });
}
// ID 중복체크
function checkUserId(memberId: string) {
  // console.log(memberId);
  return instance.get(`register/user-id/${memberId}`);
}

// ID 중복체크
function checkNickName(nickName: string) {
  // console.log(memberId);
  return instance.get(`register/nickname/${nickName}`);
}

//Email 중복체크
function checkEmail(email: string) {
  // console.log(email);
  return instance.get(`register/email/${email}`);
}
//전화번호 중복체크
function checkPhone(phonenum: string) {
  // console.log(email);
  return instance.get(`register/phone-number/${phonenum}`);
}

// 로그인 API
function loginUser(member: object) {
  return instance.post("member/login", null, { params: member });
}

export {
  registerUser,
  checkUserId,
  checkNickName,
  checkEmail,
  checkPhone,
  loginUser,
};
