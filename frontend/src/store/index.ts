/* eslint-disable prefer-const */
import { createStore, storeKey } from "vuex";

import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
} from "@/utils/cookies";
import { loginUser } from "@/api/auth";
import { getMemberInfo } from "@/api/member";
import axios from "axios";
import router from "@/router";
export default createStore({
  state: {
    // 로그인 관련
    userid: getUserFromCookie() || "",
    token: getAuthFromCookie() || "",
    uploadImageArr: [],

    //memberinfo 저장되는 정보
    // memberNo:-1
    // loginId:""
    // loginPassword:""
    // phoneNumber:""
    // nickname:""
    // email:""
    // introduce:null
    // profileName:null
    // profileUrl:null
    // exp:0
    // debateNumber:0
    // recordWin:0
    // recordDraw:0
    // recordLose:0
    // debateTime:0
    memberinfo: {}, // 로그인된 user의 정보 // 마이페이지??

    // openvidu
    OV: undefined,
    ovToken: "",
    session: undefined,
    mainStreamManager: undefined,
    publisher: undefined,
    subscribers: [],
    myNickName: "",

    // 게시판 관련
    boards: [],
    board: {},
  },
  getters: {
    isLogin(state) {
      return state.userid !== "";
    },
    isMemberInfo(state) {
      return state.memberinfo;
    },
    getBoards(state) {
      return state.boards;
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
    SET_MEMBER_INFO(state, member) {
      state.memberinfo = member;
    },
    CLEAR_MEMBER_INFO(state) {
      state.memberinfo = {};
    },
    //BOARD Mutation
    BOARDALL: (state, payload) => {
      state.boards = payload.boards;
    },
    BOARDONE: (state, payload) => {
      state.board = payload.board;
    },
  },
  actions: {
    async loginMember({ commit }, member) {
      const memberId = member.loginId;
      const response = await loginUser(member);
      console.log(response);
      commit("SET_TOKEN", response.data["access-token"]);
      commit("SET_USER_ID", memberId);
      const memberInfo = await getMemberInfo();
      console.log(memberInfo);
      commit("SET_MEMBER_INFO", memberInfo.data);
      saveAuthToCookie(response.data["access-token"]);
      saveUserToCookie(memberId);
      return response;
    },
    // BOARD Action
    BOARDALL: (store) => {
      axios.get("/debate-board").then((res) => {
        store.commit("BOARDALL", {
          boards: res.data,
        });
      });
    },
    BOARDONE: (store, num) => {
      axios.get("/debate-board/" + num).then((res) => {
        store.commit("BOARDONE", {
          board: res.data,
        });
      });
    },
    BOARDWRITE: (store, board) => {
      axios
        .post(`/debate-board/`, board)
        .then((res) => {
          console.log(res.data);
        })
        .then(() => {
          alert("게시글 등록이 완료되었습니다!!");
          router.push(`/debate-board/`);
        });
    },
    BOARDUPDATE: (store, board) => {
      axios
        .put(`/debate-board/`, board)
        .then((res) => {
          console.log(res.data);
        })
        .then(() => {
          alert("게시글 수정이 완료되었습니다!!");
          router.push(`/debate-board/`);
        });
    },
    BOARDDELETE: (store, num) => {
      let flag = confirm("정말로 삭제하시겠습니까??");
      if (flag) {
        axios
          .delete(`/debate-board/${num}`)
          .then((res) => {
            console.log(res.data);
          })
          .then(() => {
            alert("삭제되었습니다!!");
            router.push(`/debate-board/`);
          });
      }
    },
    //MyPage Action
    MEMBERPROFILE: (store) => {
      axios.get("/member/profile").then((res) => {
        store.commit("MEMBERPROFILE", {
          member: res.data,
        });
      });
    },
    MEMBEREXP: (store) => {
      axios.get("/member/exp").then((res) => {
        store.commit("MEMBEREXP", {
          member: res.data,
        });
      });
    },
    MEMBERRECORD: (store) => {
      axios.get("/member/record").then((res) => {
        store.commit("MEMBERRECORD", {
          member: res.data,
        });
      });
    },
    DEBATERECRUIT: (store) => {
      axios.get("/debate-apply/recruiting").then((res) => {
        store.commit("DEBATERECRUIT", {
          debate_board: res.data,
        });
      });
    },
    DEBATEAPPLY: (store) => {
      axios.get("/debate-apply/applying").then((res) => {
        store.commit("DEBATEAPPLY", {
          debate_board: res.data,
        });
      });
    },
    PROFILEUPDATE: (store, member) => {
      axios.put("/member/introduce", member).then(() => {
        alert("수정이 완료되었습니다!!!");
      });
    },
  },
});
