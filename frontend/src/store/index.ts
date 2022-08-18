/* eslint-disable prefer-const */
import { createStore, storeKey } from "vuex";

import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
} from "@/utils/cookies";
import { loginUser } from "@/api/auth";
import {
  getMemberInfo,
  getExpInfo,
  getRecord,
  putIntroduce,
  putProfileImage,
  postAuthPw,
  putPassword,
  deleteMember,
} from "@/api/member";
// import { putProfileImage } from "@/api/member3";
import {
  postApply,
  putApply,
  deleteApply,
  getDebateApply,
  getDebateRecruit,
} from "@/api/debateApply";
import {
  getBoards,
  getBoard,
  postBoard,
  putBoard,
  deleteBoard,
} from "@/api/board";
import { postReply, putReply, deleteReply } from "@/api/reply";
import axios from "axios";
import router from "@/router";

const API_BASE_URL = "https://i7a508.p.ssafy.io/api/";

export default createStore({
  state: {
    // 로그인 관련
    userid: getUserFromCookie() || "",
    token: getAuthFromCookie() || "",

    // 룸 내부 정보
    uploadImageArr: [],
    uploadImageFileArr: [],
    selectedFileIndex: -1,
    roomId: "",
    isRoomHost: false,
    mySideOrder: "",

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
    debate_board: {
      board_no: 0,
      board_title: "",
      max_applicant: 0,
    },
    applicant_recruit_array: [],
    applicant_recruit: {
      board_no: 0,
      order: 0,
      side: 0,
    },
    applicant_apply_array: [],
    applicant_apply: {
      board_no: 0,
      order: 0,
      side: 0,
    },
    applicant: {
      board_no: 0,
      order: 0,
      side: 0,
    },
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
    getBoard(state) {
      return state.debate_board;
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
      console.log(state.boards);
    },
    BOARDONE: (state, payload) => {
      state.debate_board = payload.board;
      // console.log(state.debate_board);
    },
    //USERPAGE Mutation
    MEMBERPROFILE: (state, payload) => {
      state.memberinfo = payload.memberinfo;
    },
    MEMBERRECORD: (state, payload) => {
      state.memberinfo = payload.memberinfo;
    },
    DEBATERECRUIT: (state, payload) => {
      console.log("11");
      console.log(state.applicant_recruit);
      state.applicant_recruit_array = payload.applicant;
      console.log(state.applicant_recruit);
    },
    DEBATEAPPLY: (state, payload) => {
      console.log("22");
      console.log(state.applicant_apply);
      state.applicant_apply_array = payload.applicant;
      console.log(state.applicant_apply);
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
      axios
        .get(`${API_BASE_URL}debate-board`, {
          headers: {
            "access-token": store.state.token,
          },
        })
        .then((res) => {
          store.commit("BOARDALL", {
            boards: res.data,
          });
        });
      // getBoards().then((res: any) => {
      //   store.commit("BOARDALL", {
      //     boards: res.data,
      //   });
      // });
    },
    BOARDONE: (store, num) => {
      return axios
        .get(`${API_BASE_URL}debate-board/${num}`, {
          headers: {
            "access-token": store.state.token,
          },
        })
        .then((res) => {
          console.log(res);
          store.commit("BOARDONE", {
            board: res.data,
          });
        });
      // getBoard(num).then((res: any) => {
      //   store.commit("BOARDONE", {
      //     board: res.data,
      //   });
      // });
    },
    BOARDWRITE: (store, board) => {
      axios
        .post(`${API_BASE_URL}debate-board/`, board, {
          headers: {
            "access-token": store.state.token,
          },
        })
        .then((res) => {
          console.log(res.data);
        })
        .then(() => {
          alert("게시글 등록이 완료되었습니다!!");
          router.push(`/`);
        });
    },
    BOARDUPDATE: (store, board) => {
      axios
        .put(`${API_BASE_URL}debate-board/`, board, {
          headers: {
            "access-token": store.state.token,
          },
        })
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
          .delete(`${API_BASE_URL}debate-board/${num}`, {
            headers: {
              "access-token": store.state.token,
            },
          })
          .then((res) => {
            console.log("!1");
            console.log(num);
            console.log(res.data);
          })
          .then(() => {
            alert("삭제되었습니다!!");
            router.push(`/debate-board/`);
          });
      }
    },
    POSTAPPLY: (store, applicant) => {
      // postApply().then((res: any) => {
      //   console.log(res.data);
      // });
      axios
        .post(`${API_BASE_URL}debate-apply`, applicant, {
          headers: {
            "access-token": store.state.token,
          },
        })
        .then((res) => {
          console.log(res.data);
        })
        .then(() => {
          alert("토론 신청이 완료되었습니다!!");
          router.push(`/debate-board/`);
        });
    },
    //MyPage Action
    MEMBERPROFILE: (store) => {
      getMemberInfo().then((res: any) => {
        store.commit("MEMBERPROFILE", {
          memberinfo: res.data,
        });
      });
    },
    MEMBEREXP: (store) => {
      getExpInfo().then((res: any) => {
        store.commit("MEMBEREXP", {
          memberinfo: res.data,
        });
      });
    },
    MEMBERRECORD: (store) => {
      getRecord().then((res: any) => {
        store.commit("MEMBERRECORD", {
          memberinfo: res.data,
        });
      });
    },
    DEBATERECRUIT: (store) => {
      return getDebateRecruit().then((res: any) => {
        store.commit("DEBATERECRUIT", {
          applicant: res.data,
        });
      });
    },
    DEBATEAPPLY: (store) => {
      getDebateApply().then((res: any) => {
        store.commit("DEBATEAPPLY", {
          applicant: res.data,
        });
      });
    },
    PROFILEIMAGE: (store) => {
      putProfileImage().then((res: any) => {
        store.commit("PROFILEIMAGE", {
          memberinfo: res.data,
        });
      });
    },
    // PROFILEIMAGE: (store, board) => {
    //   axios.put(`${API_BASE_URL}/profile-image/`).then((res) => {
    //     console.log(res.data);
    //   });
    // },
    PROFILEUPDATE: (store) => {
      putIntroduce().then((res: any) => {
        store.commit("PROFILEUPDATE", {
          memberinfo: res.data,
        });
      });
    },
    PASSWORD: (store) => {
      putPassword().then((res: any) => {
        store.commit("PROFILEUPDATE", {
          memberinfo: res.data,
        });
      });
    },
    AUTHPW: (store) => {
      postAuthPw().then((res: any) => {
        store.commit("AUTHPW", {
          memberinfo: res.data,
        });
      });
    },
    PROFILEDELETE: (store) => {
      let flag = confirm("정말로 탈퇴하시겠습니까??");
      if (flag) {
        deleteMember()
          .then((res: { data: any }) => {
            store.commit("PROFILEDELETE", {
              memberinfo: res.data,
            });
          })
          .then(() => {
            alert("또 뵐 수 있는 날을 기다리고 있겠습니다!!");
            router.push("/");
          });
      }
    },
  },
});
