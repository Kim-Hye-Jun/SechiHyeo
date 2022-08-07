import { createStore } from "vuex";

import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
} from "@/utils/cookies";
import { loginUser } from "@/api/auth";

export default createStore({
  state: {
    userid: getUserFromCookie() || "",
    token: getAuthFromCookie() || "",
    // 로그인 관련
    memberinfo: {}, // 로그인된 user의 정보
  },
  getters: {
    isLogin(state) {
      return state.userid !== "";
    },
  },
  mutations: {
    SET_USER_ID(state, userid) {
      state.userid = userid;
    },
    CLEAR_USER_ID(state) {
      state.userid = "";
    },
    SET_TOKEN(state, token) {
      state.token = token;
    },
    CLEAR_TOKEN(state) {
      state.token = "";
    },
  },
  actions: {
    async LOGIN({ commit }, member) {
      const memberId = member.loginId;
      const response = await loginUser(member);
      console.log(member);
      // console.log(response);
      commit("SET_TOKEN", response.data["access-token"]);
      commit("SET_USER_ID", memberId);
      saveAuthToCookie(response.data["access-token"]);
      saveUserToCookie(memberId);
      return response;
    },
  },
});
