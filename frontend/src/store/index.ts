import { createStore } from "vuex";
import router from "@/router";
import axios from "axios";

const REST_API = `http://localhost:9999`;

export default createStore({
  state: {
    username: "",
    // 로그인 관련
    isLogin: false, // 로그인 상태
    memberinfo: {}, // 로그인된 user의 정보
    // 회원가입 관련
    checkedId: false, // id 중복체크완료 여부
    checkedEmail: false, // eamil 중복체크완료 여부
    compare_id: "", // 중복확인 완료 id값 임시저장
    compare_email: "", // 중복확인 완료 email값 임시저장
    // 비밀번호 재설정 관련
    isAuthPw: false, // 비밀번호 재설정 권한
    tmp_userid: "", // 임시 저장 아이디
    // 프로필 재설정 관련
    isAuthProfile: false, // 프로필 재설정 권한
  },
  getters: {
    isLogin(state) {
      return state.username !== "";
    },
  },
  mutations: {
    MEMBER_LOGIN(state, member) {
      state.isLogin = true;
      state.memberinfo = member;
    },
    DUPL_RESET(state) {
      state.checkedId = false;
      state.compare_id = "";
      state.checkedEmail = false;
      state.compare_email = "";
    },
    MEMBER_LOGOUT(state) {
      localStorage.removeItem("access-token");
      state.isLogin = false;
      state.memberinfo = {};
    },
  },
  actions: {
    memberLogin({ dispatch }, member) {
      console.log("로그인 시작");
      const API_URL = `${REST_API}/register/login`;
      axios({
        url: API_URL,
        method: "POST",
        params: member,
      })
        .then((res) => {
          if (res.data.message == "success") {
            console.log(res);
            localStorage.setItem("access-token", res.data["access-token"]);
            dispatch("getMemberInfo");
            // console.log(123456);
          } else {
            alert("아이디 또는 비밀번호가 올바르지 않습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMemberInfo({ commit }) {
      const API_URL = `${REST_API}/register/getMember`;
      const ACESS_TOKEN = localStorage.getItem("access-token");
      if (ACESS_TOKEN) {
        axios({
          url: API_URL,
          method: "GET",
          headers: {
            "access-token": ACESS_TOKEN,
          },
        })
          .then((res) => {
            commit("MEMBER_LOGIN", res.data);
            router.push("main");
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    memberSignup({ commit }, member) {
      const API_URL = `${REST_API}/register/signup`;
      axios({
        url: API_URL,
        method: "POST",
        params: member,
      })
        .then(() => {
          alert(`${member.nickname}님, 회원가입을 축하드립니다!`);
          commit("DUPL_RESET");
          router.push({ name: "memberLogIn" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
