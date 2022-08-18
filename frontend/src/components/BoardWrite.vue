<template>
  <div style="position: relative; margin-top: 100px">
    <Background></Background>
    <!-- <div style="position: relative; margin-top: 100px"> -->
    <!-- <input
      id="subject"
      v-model="debate_board.board_title"
      type="text"
      placeholder="게시글 제목 입력"
    /> -->
    <!-- <input
      id="topic"
      v-model="debate_board.debate_topic"
      type="text"
      placeholder="토론 주제 입력"
    /> -->
    <!-- <input
      id="a"
      v-model="debate_board.a_opinion"
      type="text"
      placeholder="A 진영 주장 입력"
    />
    <input
      id="b"
      v-model="debate_board.b_opinion"
      type="text"
      placeholder="B 진영 주장 입력"
    /> -->
    <!-- <input
      id="day"
      v-model="debate_board.debate_time"
      type="datetime-local"
      placeholder="토론 일시 입력"
    /> -->
    <!-- <input
      id="content"
      v-model="debate_board.board_content"
      type="text"
      placeholder="내용 입력"
    /> -->
    <!-- <input
      id="max"
      v-model="debate_board.max_applicant"
      type="number"
      placeholder="정원 입력"
    /> -->
    <!-- <button @click="boardWrite()">등록</button> -->
    <!-- </div> -->
    <span class="span-modal" style="left: -350px"></span>
    <span class="span-modal" style="top: -350px"></span>
    <span class="span-modal" style="left: 350px"></span>
    <span class="span-modal" style="top: 350px"></span>
    <div class="modal-container">
      <header class="modal-container-header">
        <!-- <h1 class="modal-container-title">
          토론 주제 : {{ debate_board.debate_topic }}
        </h1> -->
        <input
          id="topic"
          v-model="debate_board.debate_topic"
          type="text"
          placeholder="토론 주제 입력"
          class="modal-container-title"
        />
        <input
          id="subject"
          v-model="debate_board.board_title"
          type="text"
          placeholder="게시글 제목 입력"
        />
      </header>
      <div class="modal-container-body article">
        <input
          id="day"
          v-model="debate_board.debate_time"
          type="datetime-local"
          placeholder="토론 일시 입력"
          class="board-day"
        />
        <!-- <h3 class="board-day">토론 일시 : {{ debate_board.debate_time }}</h3> -->
        <!-- <h3 class="board-count">
          인원 : {{ debate_board.current_applicant }}/{{
            debate_board.max_applicant
          }}
        </h3> -->
        <input
          id="max"
          v-model="debate_board.max_applicant"
          type="number"
          placeholder="정원 입력"
          class="board-count"
        />
        <h2 class="board-summary">개요</h2>
        <div class="board-summary-content">
          <input
            id="a"
            v-model="debate_board.a_opinion"
            type="text"
            placeholder="A 진영 주장 입력"
            class="board-a"
          />
          <input
            id="b"
            v-model="debate_board.b_opinion"
            type="text"
            placeholder="B 진영 주장 입력"
            class="board-b"
          />
          <!-- <li class="board-a">A 진영 : {{ debate_board.a_opinion }}</li>
          <li class="board-b">B 진영 : {{ debate_board.b_opinion }}</li> -->
          <!-- <p class="board-summary-in">
            CONTENT {{ debate_board.board_content }}
          </p> -->
          <input
            id="content"
            v-model="debate_board.board_content"
            type="text"
            placeholder="내용 입력"
            class="board-summary-in"
          />
        </div>
        <button @click="boardWrite()">등록</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { mapActions, mapState } from "vuex";
import Background from "@/components/common/Background.vue";
import { defineComponent } from "vue";

export default defineComponent({
  components: { Background },
  data() {
    return {
      board: {
        board_title: "",
        board_content: "",
        debate_topic: "",
        debate_time: Date,
        a_opinion: "",
        b_opinion: "",
        max_applicant: 0,
      },
    };
  },
  computed: {
    ...mapState(["debate_board"]),
  },
  methods: {
    ...mapActions(["BOARDWRITE"]),
    boardWrite() {
      this.BOARDWRITE({
        board_title: this.debate_board.board_title,
        board_content: this.debate_board.board_content,
        debate_topic: this.debate_board.debate_topic,
        debate_time: this.debate_board.debate_time,
        a_opinion: this.debate_board.a_opinion,
        b_opinion: this.debate_board.b_opinion,
        max_applicant: this.debate_board.max_applicant,
      });
    },
    // back() {
    //   this.$router.go(-1);
    // },
  },
});
</script>

<style scoped>
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
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(#000, 0.25);
}
.modal-container {
  margin-left: auto;
  margin-right: auto;
  background: #757f9a;
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
  /* font-family: "Times New Roman", Times, serif; */
  /* font-weight: 700; */
  font-size: 1.125;
  color: #000000;
}
.modal-container-body {
  padding: 24px 32px 51px;
  /* font-family: serif; */
  overflow-y: auto;
  color: #000000;
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
  color: #000000;
  border: 0;
  font-weight: 600;
  font-size: 20px;
  /* font-family: "Times New Roman", Times, serif; */
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
.modalZero {
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
.board-master {
  position: relative;
  display: inline-block;
  top: -20px;
  left: -50px;
  width: 120px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 600; */
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  border-bottom: 1px solid #ddd;
}
.board-day {
  position: relative;
  display: inline-block;
  top: -20px;
  width: 270px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 600; */
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  border-bottom: 1px solid #ddd;
}
.board-count {
  position: relative;
  display: inline-block;
  top: -20px;
  right: -50px;
  width: 100px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 600; */
  font-size: 16px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  border-bottom: 1px solid #ddd;
}
.board-summary {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 240px;
  top: -60px;
  left: -34px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 800; */
  font-size: 20px;
  color: #000000;
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
  color: #000000;
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
  color: #000000;
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
</style>
