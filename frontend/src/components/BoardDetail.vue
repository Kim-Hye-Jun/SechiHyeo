<template>
  <span class="span-modal" style="left: -350px"></span>
  <span class="span-modal" style="top: -350px"></span>
  <span class="span-modal" style="left: 350px"></span>
  <span class="span-modal" style="top: 350px"></span>
  <div class="modal-container">
    <header class="modal-container-header">
      <h1 class="modal-container-title">토론 주제 : {{ board.title }}</h1>
    </header>
    <div class="modal-container-body article">
      <h3 class="board-master">작성자 : {{ board.nickname }}</h3>
      <h3 class="board-day">토론 일시 : {{ board.debate_time }}</h3>
      <h3 class="board-count">인원 : {{ board.max_applicant }}</h3>
      <h2 class="board-summary">개요</h2>
      <div class="board-summary-content">
        <li class="board-a">A 진영 : {{ board.a_opinion }}</li>
        <li class="board-b">B 진영 : {{ board.b_opinion }}</li>
        <p class="board-summary-in">CONTENT {{ board.board_content }}</p>
      </div>
      <div class="board-reply-box">
        <h3 class="board-reply">댓글</h3>
        <h5 class="board-reply-count">{{ reply.depth }} 개</h5>
      </div>
      <img class="board-reply-create-profile" :src="member.profile_url" />
      <textarea class="board-reply-create-input" placeholder="댓글"></textarea>
      <button class="board-reply-create-sign" @click="replyInsert()">
        등록
      </button>
      <!-- 댓글 반복 -->
      <div v-for="reply in replies" :key="reply.reply_no">
        <img class="board-reply-ex-profile" :src="reply.profile_url" />
        <p class="board-reply-ex-user">User {{ reply.nickname }}</p>
        <p class="board-reply-ex-user-content">댓글 {{ reply.context }}</p>
        <button
          class="board-reply-ex-delete"
          @click="replyDelete(reply.reply_no)"
        >
          삭제
        </button>
        <button
          class="board-reply-ex-update"
          @click="replyUpdate(reply.reply_no)"
        >
          수정
        </button>
        <!-- 대댓글 토글 버튼 -->
        <button class="board-reply-ex-reply">답글</button>
        <img class="board-reply2-create-profile" :src="member.profile_url" />
        <textarea
          class="board-reply2-create-input"
          placeholder="대댓글"
        ></textarea>
        <button class="board-reply2-create-sign" @click="replyInsert()">
          등록
        </button>
        <!-- 대댓글 반복 -->
        <div
          class="board-reply2-ex"
          v-for="reply in replies"
          :key="reply"
          :filter="reply.reply_no"
          :filter-function="reReply(reply.parent_no, reply.reply_no)"
        >
          <image class="board-reply2-ex-profile" :src="reply.profile_url" />
          <p class="board-reply2-ex-user">User {{ reply.nickname }}</p>
          <p>대댓글 {{ reply.context }}</p>
          <button
            class="board-reply2-ex-delete"
            @click="replyDelete(reply.reply_no)"
          >
            삭제
          </button>
          <button
            class="board-reply2-ex-update"
            @click="replyUpdate(reply.reply_no)"
          >
            수정
          </button>
        </div>
      </div>
    </div>
    <footer class="modal-container-footer">
      <button class="button decline modalOut" @click="modalOut">
        뒤로가기
      </button>
      <!-- 참가신청 버튼 메소드 미작성 -->
      <!-- <button class="button accept" @click="apply">참가신청</button> -->
      <button class="button accept">참가신청</button>
    </footer>
  </div>
</template>

<script lang="ts">
import axios from "axios";
import { defineComponent } from "vue";
import { mapActions, mapState } from "vuex";
export default defineComponent({
  data() {
    return {
      modal: true,
      member: {
        profile_url: "",
      },
      boards: [],
      board: {
        board_no: 0,
        board_title: "",
        debate_topic: "",
        board_content: "",
        debate_time: "",
        max_applicant: 0,
        a_opinion: "",
        b_opinion: "",
        nickname: "",
      },
      replies: [],
      reply: {
        reply_no: 0,
        context: "",
        date: "",
        depth: 0,
        hidden: "",
        board_no: 0,
        parent_no: 0,
        member_no: 0,
        profile_url: "",
        nickname: "",
      },
    };
  },
  computed: {
    ...mapState(["boards", "board"]),
  },
  created() {
    // 이 방법이 맞나 BE 검수 필요
    this.boardOne(this.board.board_no);
    this.replyAll();
  },
  methods: {
    ...mapActions(["BOARDONE"]),
    modalOut() {
      this.modal = false;
    },
    boardOne(no: number) {
      this.BOARDONE(no);
    },
    // 자식 대댓글만을 걸러내기 위한 부모 댓글 필터
    reReply(reply: any, filter: number) {
      if (reply.parent_no === filter) {
        return true;
      }
      return false;
    },
    replyAll() {
      axios.get("debate-reply/" + this.reply.reply_no).then((res) => {
        this.replies = res.data;
      });
    },
    replyInsert() {
      axios
        .post("debate-reply/" + this.board.board_no, {
          parent_no: this.reply.parent_no,
          context: this.reply.context,
          depth: this.reply.depth,
        })
        .then(() => {
          this.replyAll();
        });
    },
    replyUpdate(reply_no: number) {
      axios.put(`debate-reply/`, reply_no).then((res) => {
        console.log(res.data);
      });
    },
    replyDelete(reply_no: number) {
      let flag = confirm("정말로 삭제하시겠습니까??");
      if (flag) {
        axios.delete("debate-reply/" + reply_no).then(() => {
          this.replyAll();
        });
      }
    },
  },
});
</script>

<style>
button,
input,
select,
textarea {
  font: inherit;
}
a {
  color: inherit;
}
* {
  scrollbar-width: 0;
}
*::-webkit-scrollbar {
  background-color: transparent;
  width: 12px;
}
*::-webkit-scrollbar-thumb {
  border-radius: 99px;
  background-color: #ddd;
  border: 2px solid #fff;
}
.modal {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(#000, 0.25);
}
.modal-container {
  margin-left: auto;
  margin-right: auto;
  background-color: #111845;
  border-radius: 10px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  box-shadow: 0 15px 30px 0 rgba(#000, 0.25);
  @media (max-width: 600px) {
    width: 100%;
  }
}
.modal-container-header {
  padding: 16px 32px;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.modal-container-title {
  display: flex;
  align-items: center;
  gap: 8px;
  line-height: 1;
  font-family: "Times New Roman", Times, serif;
  font-weight: 700;
  font-size: 1.125;
  color: white;
}
.modal-container-body {
  padding: 24px 32px 51px;
  font-family: serif;
  overflow-y: auto;
  color: white;
}
.artcicle {
  h1 {
    font-size: 1.5rem;
    line-height: 1.125;
  }
  h2 {
    font-size: 1.25rem;
    line-height: 1.25;
  }
  h3 {
    font-size: 1rem;
    line-height: 1.5;
  }
  ul,
  ol {
    margin-left: 20px;
    list-style-position: inside;
  }
  ol {
    list-style: numeric;
  }
  ul {
    list-style: disc;
  }
}
.modal-container-footer {
  padding: 20px 32px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  border-top: 1px solid #ddd;
  gap: 12px;
  position: relative;
}
.button {
  padding: 12px 20px;
  border-radius: 8px;
  background-color: transparent;
  color: white;
  border: 0;
  font-weight: 600;
  font-size: 20px;
  font-family: "Times New Roman", Times, serif;
  cursor: pointer;
  transition: 0.15s ease;
}
.icon-button {
  padding: 0;
  border: 0;
  background-color: transparent;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  line-height: 1;
  cursor: pointer;
  border-radius: 8px;
  transition: 0.15s ease;
}
.modal {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 700px;
  height: 700px;
  background: #111845a6;
  box-sizing: border-box;
  overflow: hidden;
  box-shadow: 0 20px 50px rgb(23, 32, 90);
  border: 2px solid #2a3cad;
  padding: 20px;
}
.modal:before {
  content: "";
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.1);
  transition: 0.5s;
  pointer-events: none;
}
.modal:hover:before {
  left: -50%;
  transform: skewX(-5deg);
}
.modal .modal-container {
  position: absolute;
  top: 15px;
  left: 15px;
  right: 15px;
  bottom: 15px;
  border: 1px solid #f0a591;
  padding: 20px;
  text-align: center;
  box-shadow: 0 5px 10px rgba(9, 0, 0, 0.5);
}
.modal .span-modal {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: block;
  box-sizing: border-box;
}
.modal .span-modal:nth-child(1) {
  transform: rotate(0deg);
}
.modal .span-modal:nth-child(2) {
  transform: rotate(90deg);
}
.modal .span-modal:nth-child(3) {
  transform: rotate(180deg);
}
.modal .span-modal:nth-child(4) {
  transform: rotate(270deg);
}
.modal .span-modal:before {
  content: "";
  position: absolute;
  width: 100%;
  height: 2px;
  background: #50dfdb;
  animation: animate 4s linear infinite;
}

@keyframes animate {
  0% {
    transform: scaleX(0);
    transform-origin: left;
  }
  50% {
    transform: scaleX(1);
    transform-origin: left;
  }
  50.1% {
    transform: scaleX(1);
    transform-origin: right;
  }

  100% {
    transform: scaleX(0);
    transform-origin: right;
  }
}
.board-master {
  position: relative;
  display: inline-block;
  top: -20px;
  left: -50px;
  width: 120px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #ffffff;
  border-bottom: 1px solid #ddd;
}
.board-day {
  position: relative;
  display: inline-block;
  top: -20px;
  width: 270px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #ffffff;
  border-bottom: 1px solid #ddd;
}
.board-count {
  position: relative;
  display: inline-block;
  top: -20px;
  right: -50px;
  width: 100px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #ffffff;
  border-bottom: 1px solid #ddd;
}
.board-summary {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 240px;
  top: -60px;
  left: -34px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 800;
  font-size: 20px;
  color: #ffffff;
  border-right: 3px solid #ddd;
}
.board-summary-content {
  position: relative;
  display: inline-block;
  width: 450px;
  height: 200px;
}
.board-a {
  position: relative;
  display: inline-block;
  width: 400px;
  padding-left: 20px;
  left: -50px;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #ffffff;
}
.board-b {
  position: relative;
  display: inline-block;
  width: 400px;
  padding-left: 20px;
  left: -50px;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #ffffff;
  border-bottom: 3px solid #ddd;
}
.board-summary-in {
  position: relative;
  display: inline-block;
  width: 400px;
  height: 180px;
  top: 10px;
  left: -40px;
  text-align: left;
}
.board-reply-box {
  position: relative;
  display: inline-block;
  width: 580px;
  top: -40px;
  left: -60px;
  border-bottom: 3px solid #ddd;
}
.board-reply {
  position: relative;
  display: inline-block;
  width: 80px;
  padding-bottom: 2px;
}
.board-reply-count {
  position: relative;
  display: inline-block;
  text-align: left;
  width: 400px;
  padding-bottom: 2px;
}
.board-reply-create-profile {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 60px;
  left: -10px;
  top: -10px;
  background: #160715;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
}
.board-reply-create-input {
  position: relative;
  display: inline-block;
  width: 460px;
  height: 40px;
  top: -15px;
  color: #fff;
  padding: 5px;
  background-color: #111845;
  border-radius: 5px;
  border: 0px;
  border-bottom: 1px solid #ffffff;
}
.board-reply-create-sign {
  position: relative;
  width: 50px;
  height: 30px;
  left: 230px;
  bottom: 10px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #111845;
  transition: 0.3s;
  background-color: #ffffff;
  border-radius: 5px;
}
.board-reply-create-sign:hover {
  cursor: pointer;
  background: #03e9f4;
  color: #000000;
  border: #03e9f4 1px solid;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 100px #03e9f4;
}
.board-reply-ex-profile {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 60px;
  left: -20px;
  top: -15px;
  background: #160715;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
}
.board-reply-ex-user-content {
  position: relative;
  display: inline-block;
  width: 450px;
  height: 60px;
  text-align: left;
}
.board-reply-ex-update {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  left: 200px;
  top: -20px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply-ex-delete {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  left: 0px;
  top: -20px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply-ex-reply {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  left: 200px;
  top: -20px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply-ex-user {
  position: relative;
  display: inline-block;
  width: 56px;
  height: 30px;
  top: 60px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply2-create {
  position: relative;
  display: inline-block;
}
.board-reply2-create-profile {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 50px;
  background: #160715;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
}
.board-reply2-create-input {
  position: relative;
  display: inline-block;
  border: 1px solid #ffffff;
}
.board-reply2-create-text {
  position: relative;
  display: inline-block;
  display: inline-block;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply2-create-cancle {
  position: relative;
  display: inline-block;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply2-create-sign {
  position: relative;
  display: inline-block;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply2-create-button {
  position: relative;
  display: inline-block;
  background: #c4c4c4;
  transform: rotate(-180deg);
}
.board-reply2-ex {
  position: relative;
  display: inline-block;
  width: 200px;
}
.board-reply2-ex-update {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  top: 21px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply2-ex-delete {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  top: 21px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
.board-reply2-ex-profile {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 50px;
  left: 0px;
  top: 9px;
  background: #160715;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
}
.board-reply2-ex-user {
  position: relative;
  display: inline-block;
  width: 56px;
  height: 30px;
  left: 71px;
  top: 0px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 30px;
  color: #ffffff;
}
</style>
