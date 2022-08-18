<template>
  <div class="boards">
    <Background @click="modalOut"></Background>
    <div class="boards-title">토론 모집 게시판</div>
    <div class="boards-content">
      <!-- 게시판 버튼 -->
      <div
        class="boards-content-text"
        @click="academyClick"
        :class="[academy === true ? 'academy' : 'no']"
      >
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
        게시판
      </div>
      <!-- 글쓰기 버튼 -->
      <div
        class="boards-content-text"
        style="filter: hue-rotate(270deg)"
        @click="freeClick(), modalIn()"
        :class="[free === true ? 'free' : 'no']"
      >
        글쓰기
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
      </div>
      <!-- 글 목록 -->
      <div class="boards-content-write">
        <!-- :class="[academy === true ? '' : 'hidden']" -->
        <div
          class="boards-content-write-box1"
          @click="moveToDetail(debate_board.board_no)"
          v-for="(debate_board, board_no) in boards.slice(
            nowPage * 10,
            boards.length > nowPage * 10 + 10
              ? nowPage * 10 + 10
              : boards.length
          )"
          :key="board_no"
        >
          <div class="boards-content-write-no">
            <!-- 그냥 인덱스로 표현하기로 결정 -->
            {{ boards.length - nowPage * 10 - board_no }}
          </div>
          <div class="boards-content-write-title">
            {{ debate_board.board_title }}
          </div>
          <div class="boards-content-write-count">
            <!-- {{ debate_board["current_applicant"] }}/ -->
            {{ debate_board.max_applicant + "인" }}
          </div>
        </div>
      </div>
      <!-- 페이지네이션 -->
      <div class="btn-cover">
        <button
          :disabled="nowPage === 0"
          @click="prevPage"
          class="btn btn-outline-secondary border-radius"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-chevron-left"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"
            />
          </svg>
        </button>
        <span class="page-count"
          >&nbsp; {{ nowPage + 1 }} / {{ pageCount }} &nbsp;
        </span>
        <button
          :disabled="nowPage >= pageCount - 1"
          @click="nextPage"
          class="btn btn-outline-secondary border-radius"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-chevron-right"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"
            />
          </svg>
        </button>
      </div>
      <!-- 레이아웃용 -->
      <div class="boards-content-write">
        <!-- :class="[free === true ? '' : 'hidden']" -->
        <!-- <div
          class="boards-content-write-box1"
          v-for="(debate_board, board) in boards"
          :key="board"
          :filter="`free`"
          :filter-function="typeFree(room, '`free`')"
        >
          <div class="boards-content-write-no">
            {{ debate_board["board_no"] }}
          </div>
          <div class="boards-content-write-title">
            {{ debate_board["board_title"] }}
          </div>
          <div class="boards-content-write-count">
            {{ debate_board["current_applicant"] }}/{{
              debate_board["max_applicant"]
            }}
          </div>
        </div> -->
      </div>
    </div>
    <!-- 모달창 -->
    <div class="modalZero" :class="[modal === false ? 'hidden' : '']">
      <!-- <div @modalOut="modalOut"> -->
      <div>
        <span class="span-modal" style="left: -320px"></span>
        <span class="span-modal" style="top: -320px"></span>
        <span class="span-modal" style="left: 320px"></span>
        <span class="span-modal" style="top: 320px"></span>
        <div class="modal-container">
          <header class="modal-container-header">
            <input
              id="subject"
              v-model="debate_board.board_title"
              type="text"
              placeholder="게시글 제목 입력"
              class="board-title"
            />
          </header>
          <div class="modal-container-body">
            <!-- <div class="board-summary-content"> -->
            <input
              id="topic"
              v-model="debate_board.debate_topic"
              type="text"
              placeholder="토론 주제 입력"
              class="board-topic"
            />
            <input
              id="a"
              v-model="debate_board.a_opinion"
              type="text"
              placeholder="A 진영 주장 입력"
              class="board-a"
            />
            <input
              id="day"
              v-model="debate_board.debate_time"
              type="datetime-local"
              placeholder="토론 일시 입력"
              class="board-day"
            />
            <input
              id="b"
              v-model="debate_board.b_opinion"
              type="text"
              placeholder="B 진영 주장 입력"
              class="board-b"
            />
            <input
              id="max"
              v-model="debate_board.max_applicant"
              type="number"
              placeholder="정원 입력"
              class="board-count"
            />
            <input
              id="content"
              v-model="debate_board.board_content"
              type="text"
              placeholder="내용 입력"
              class="board-summary-in"
            />
            <!-- </div> -->
            <div>
              <button class="button append" @click="boardWrite()">등록</button>
              <button class="button modalOut" @click="modalOut">취소</button>
            </div>
          </div>
        </div>
        <!-- </div> -->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Background from "@/components/common/Background.vue";
// import BoardDetail from "./BoardDetail.vue";
import { mapActions, mapState } from "vuex";
import { defineComponent } from "vue";

export default defineComponent({
  components: { Background },
  data() {
    return {
      academy: true,
      free: false,
      modal: false,
      // boards: [],
      // board: {
      //   board_no: 0,
      //   board_title: "",
      //   max_applicant: 0,
      // },
      room: {
        debate_type: "",
      },
      board: {
        board_title: "",
        board_content: "",
        debate_topic: "",
        debate_time: "",
        a_opinion: "",
        b_opinion: "",
        max_applicant: 0,
      },
      nowPage: 0,
      pageCount: 1,
    };
  },
  computed: {
    ...mapState(["boards", "debate_board"]),
  },
  created() {
    this.boardAll().then(() => {
      this.pageCount = Math.floor(this.boards.length / 10) + 1;
    });
  },
  methods: {
    ...mapActions(["BOARDALL", "BOARDWRITE", "BOARDONE"]),
    boardAll() {
      return this.BOARDALL();
    },
    academyClick() {
      (this.academy = true), (this.free = false);
    },
    freeClick() {
      (this.academy = false), (this.free = true);
    },
    moveToDetail(board_no: number) {
      // let no = board_no + 1;
      console.log(board_no);
      this.BOARDONE(board_no).then(() => {
        this.$router.push("/debate-board/" + board_no);
      });
    },
    modalIn() {
      this.modal = true;
      console.log(this.modal);
    },
    modalOut() {
      this.modal = false;
      (this.academy = true), (this.free = false);
      console.log(this.modal);
    },
    modalInit() {
      this.board.board_title = "";
      this.board.board_content = "";
      this.board.debate_topic = "";
      this.board.debate_time = "";
      this.board.a_opinion = "";
      this.board.b_opinion = "";
      this.board.max_applicant = 0;
    },
    typeAcademy(room: { debate_type: string }, filter: string) {
      if (room.debate_type === filter) {
        return true;
      }
      return false;
    },
    typeFree(room: { debate_type: string }, filter: string) {
      if (room.debate_type === filter) {
        return true;
      }
      return false;
    },
    boardWrite() {
      this.BOARDWRITE({
        board_title: this.debate_board.board_title,
        board_content: this.debate_board.board_content,
        debate_topic: this.debate_board.debate_topic,
        debate_time: this.debate_board.debate_time,
        a_opinion: this.debate_board.a_opinion,
        b_opinion: this.debate_board.b_opinion,
        max_applicant: this.debate_board.max_applicant,
      }).then(() => {
        this.pageCount = Math.floor(this.boards.length / 10) + 1;
        this.modalInit();
        this.modalOut();
      });
    },
    nextPage() {
      this.nowPage += 1;
    },
    prevPage() {
      this.nowPage -= 1;
    },
  },
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@400;700&display=swap");
* {
  margin: 0;
}
.page-count {
  color: white;
}
.boards {
  text-align: center;
  font-family: "Black Han Sans";
}
.hidden {
  display: none;
}
.academy {
  background: #03e9f4;
  color: #050801;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 200px #03e9f4;
  -webkit-box-reflect: below 1px linear-gradient(transparent, #0005);
}
.no {
  background: #050801;
}
.free {
  background: #03e9f4;
  color: #050801;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 200px #03e9f4;
  -webkit-box-reflect: below 1px linear-gradient(transparent, #0005);
}
.boards {
  margin-left: 50%;
  transform: translate(-50%);
  width: 100vw;
  height: 100vh;
}
.boards-title {
  position: absolute;
  width: 820px;
  height: 120px;
  top: 90px;
  left: 50%;
  transform: translate(-50%);
  font-size: 80px;
  text-align: center;
  color: #ffffff;
  font-style: italic;
  font-family: "Raleway", sans-serif;
}
.boards-title::after {
  content: "토론 모집 게시판";
  position: absolute;
  top: 5px;
  left: 51%;
  transform: translate(-51%);
  text-align: center;
  color: rgb(125, 125, 125);
  z-index: -1;
  opacity: 0.4;
  white-space: nowrap;
}
.boards-content {
  position: absolute;
  top: 200px;
  left: 50%;
  transform: translate(-50%);
}
.boards-content-text {
  position: relative;
  display: inline-block;
  padding: 10px 10px;
  color: #03e9f4;
  text-decoration: none;
  text-transform: uppercase;
  font-family: "Raleway", sans-serif;
  font-weight: bold;
  font-size: 24px;
  transition: 0.5s;
  letter-spacing: 4px;
  overflow: hidden;
  width: 150px;
  margin: 0px 80px 10px 80px;
}
.boards-content-text:hover {
  background: #03e9f4;
  color: #050801;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 200px #03e9f4;
  -webkit-box-reflect: below 1px linear-gradient(transparent, #0005);
}
.boards-content-text .span-board {
  position: absolute;
  display: block;
}
div .span-board:nth-child(1) {
  top: 0;
  left: 0;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #03e9f4);
  animation: animate1 1s linear infinite;
}
@keyframes animate1 {
  0% {
    left: -100%;
  }
  50%,
  100% {
    left: 100%;
  }
}
div .span-board:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #03e9f4);
  animation: animate2 1s linear infinite;
  animation-delay: 0.25s;
}
@keyframes animate2 {
  0% {
    top: -100%;
  }
  50%,
  100% {
    top: 100%;
  }
}
div .span-board:nth-child(3) {
  bottom: 0;
  right: 0;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #03e9f4);
  animation: animate3 1s linear infinite;
  animation-delay: 0.5s;
}
@keyframes animate3 {
  0% {
    right: -100%;
  }
  50%,
  100% {
    right: 100%;
  }
}
div .span-board:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: animate4 1s linear infinite;
  animation-delay: 0.75s;
}
@keyframes animate4 {
  0% {
    bottom: -100%;
  }
  50%,
  100% {
    bottom: 100%;
  }
}
.boards-content-free {
  position: absolute;
  width: 200px;
  height: 50px;
  right: 10%;
  top: 0%;
}
.boards-content-write-box1 {
  position: relative;
  margin: 10px;
  width: 800px;
  height: 42px;
  background: #757f9a;
  border-radius: 15px;
}
.boards-content-write-box2 {
  position: relative;
  margin: 10px;
  width: 800px;
  height: 42px;
  background: #d7dde8;
  border-radius: 15px;
}
.boards-content-write-no {
  position: absolute;
  left: 0%;
  right: 85%;
  font-family: "Inter";
  font-style: normal;
  font-weight: 500;
  font-size: 18px;
  line-height: 44px;
  color: #ffffff;
}
.boards-content-write-title {
  position: absolute;
  left: 15%;
  font-family: "Inter";
  font-style: normal;
  font-weight: 500;
  font-size: 18px;
  line-height: 44px;
  color: #ffffff;
}
.boards-content-write-count {
  position: absolute;
  left: 90%;
  right: 0%;
  font-family: "Inter";
  font-style: italic;
  font-weight: 500;
  font-size: 18px;
  line-height: 44px;
  color: #ffffff;
}
.common-create {
  position: absolute;
  width: 80px;
  height: 80px;
  right: 100px;
  top: 910px;
}
.common-create-button {
  position: absolute;
  width: 80px;
  height: 80px;
  left: 0px;
  top: 0px;
}
.common-create-circle {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  background-image: linear-gradient(135deg, #f05f57 10%, #360940 100%);
  border-radius: 15px 15px 15px 15px;
}
.common-create-plus {
  position: absolute;
  left: 23.75%;
  right: 25%;
  top: 22.5%;
  bottom: 22.5%;
  background-image: linear-gradient(135deg, #72edf2 10%, #5151e5 100%);
  border-radius: 30px 30px 30px 30px;
}
.hidden {
  display: none;
}
.academy {
  background: #03e9f4;
  color: #050801;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 200px #03e9f4;
  -webkit-box-reflect: below 1px linear-gradient(transparent, #0005);
}
.no {
  background: #050801;
}
.free {
  background: #03e9f4;
  color: #050801;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 200px #03e9f4;
  -webkit-box-reflect: below 1px linear-gradient(transparent, #0005);
}
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
.modalZero {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* display: flex; */
  align-items: center;
  justify-content: center;
  background-color: rgba(#000, 0.25);
}
.modal-container {
  margin-left: auto;
  margin-right: auto;
  background: #757f9a;
  /* background: #d7dde8; */
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
  background: #d7dde8;
  border-radius: 10px solid black;
  align-items: center;
  gap: 8px;
  line-height: 1;
  font-family: "Times New Roman", Times, serif;
  font-weight: 700;
  font-size: 1.125;
  color: #000000;
}
.modal-container-body {
  /* padding: 24px 32px 51px; */
  font-family: serif;
  overflow-y: auto;
  color: #000000;
}
.modalZero {
  position: absolute;
  margin-top: 20px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 640px;
  height: 640px;
  background: #111845a6;
  box-sizing: border-box;
  overflow: hidden;
  box-shadow: 0 20px 50px rgb(23, 32, 90);
  border: 2px solid #2a3cad;
  padding: 20px;
}
.modalZero:before {
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
.modalZero:hover:before {
  left: -50%;
  transform: skewX(-5deg);
}
.modalZero .modal-container {
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
.modalZero .span-modal {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: block;
  box-sizing: border-box;
}
.modalZero .span-modal:nth-child(1) {
  transform: rotate(0deg);
}
.modalZero .span-modal:nth-child(2) {
  transform: rotate(90deg);
}
.modalZero .span-modal:nth-child(3) {
  transform: rotate(180deg);
}
.modalZero .span-modal:nth-child(4) {
  transform: rotate(270deg);
}
.modalZero .span-modal:before {
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
.board-day {
  position: relative;
  display: inline-block;
  margin-top: 5px;
  margin-left: 10px;
  width: 100px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  border-bottom: 1px solid #ddd;
}
.board-count {
  position: relative;
  display: inline-block;
  width: 60px;
  margin-top: 5px;
  margin-left: 50px;
  margin-bottom: 5px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  border-bottom: 1px solid #ddd;
}
.board-a {
  position: relative;
  display: inline-block;
  margin-bottom: 5px;
  width: 400px;
  font-style: normal;
  font-weight: bold;
  font-size: 16px;
  text-align: left;
  line-height: 30px;
  color: #000000;
  border-radius: 10px;
}
.board-a:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.board-b {
  position: relative;
  display: inline-block;
  width: 400px;
  /* font-style: normal; */
  font-weight: bold;
  font-size: 16px;
  text-align: left;
  line-height: 30px;
  color: #000000;
  border-radius: 10px;
  /* border-bottom: 3px solid #ddd; */
}
.board-b:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.board-summary-in {
  position: relative;
  display: inline-block;
  background: #d7dde8;
  border-radius: 10px;
  width: 500px;
  height: 280px;
  top: 5px;
  left: 0px;
  text-align: left;
}
.board-summary-in:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.board-title {
  position: relative;
  display: inline-block;
  background: #d7dde8;
  width: 540px;
  height: 40px;
  font-size: 16px;
  text-align: left;
  border-radius: 10px;
}
.board-title:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.board-topic {
  position: relative;
  display: inline-block;
  background: #d7dde8;
  margin-top: 10px;
  margin-bottom: 5px;
  width: 500px;
  height: 36px;
  text-align: left;
  border-radius: 10px;
}
.board-topic:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.button {
  width: 80px;
  height: 40px;
  border-radius: 5px;
  font-size: bold;
  margin-top: 20px;
  margin-right: 5px;
  margin-left: 5px;
}
.button:hover {
  background-color: #050801;
  color: white;
  box-shadow: 0 0 0.5px #050801, 0 0 2.5px #050801, 0 0 5px #050801,
    0 0 10px #050801;
}
</style>
