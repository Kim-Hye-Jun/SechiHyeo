<template>
  <div class="boards">
    <Background @click="modalOut"></Background>
    <div class="boards-title">토론 모집 게시판</div>
    <div class="boards-content">
      <div
        class="boards-content-text"
        @click="academyClick"
        :class="[academy === true ? 'academy' : 'no']"
      >
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
        아카데미
      </div>
      <div
        class="boards-content-text"
        style="filter: hue-rotate(270deg)"
        @click="freeClick"
        :class="[free === true ? 'free' : 'no']"
      >
        자유
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
        <span class="span-board"></span>
      </div>
      <div
        class="boards-content-write"
        :class="[academy === true ? '' : 'hidden']"
      >
        <div
          class="boards-content-write-box2"
          @click="modalIn"
          v-for="board in boards"
          :key="board"
          :filter="`academy`"
          :filter-function="typeAcademy(room, '`academy`')"
        >
          <div class="boards-content-write-no">{{ board.boardNo }}</div>
          <div class="boards-content-write-title">
            {{ board.boardTitle }}
          </div>
          <div class="boards-content-write-count">
            {{ board.currentApplicant }}/{{ board.maxApplicant }}
          </div>
        </div>
        <div class="boards-content-write-box1" @click="modalIn">
          <div class="boards-content-write-no">10787</div>
          <div class="boards-content-write-title">제목제목제목제목</div>
          <div class="boards-content-write-count">6/6</div>
        </div>
      </div>
      <div
        class="boards-content-write"
        :class="[free === true ? '' : 'hidden']"
      >
        <div
          class="boards-content-write-box1"
          @click="modalIn"
          v-for="board in boards"
          :key="board"
          :filter="`free`"
          :filter-function="typeFree(room, '`free`')"
        >
          <div class="boards-content-write-no">{{ board.boardNo }}</div>
          <div class="boards-content-write-title">{{ board.boardTitle }}</div>
          <div class="boards-content-write-count">
            {{ board.currentApplicant }}/{{ board.maxApplicant }}
          </div>
        </div>
        <div class="boards-content-write-box2" @click="modalIn">
          <div class="boards-content-write-no">10788</div>
          <div class="boards-content-write-title">제목제목제목제목</div>
          <div class="boards-content-write-count">6/6</div>
        </div>
      </div>
    </div>
    <div class="modal" :class="[modal === false ? 'hidden' : '']">
      <board-detail></board-detail>
    </div>
  </div>
</template>

<script lang="ts">
import Background from "@/components/common/Background.vue";
import BoardDetail from "./BoardDetail.vue";
import { mapActions, mapState } from "vuex";
import { defineComponent } from "vue";

export default defineComponent({
  components: { Background, BoardDetail },
  data() {
    return {
      academy: true,
      free: false,
      modal: false,
      boards: [],
      board: {
        boardNo: "",
        boardTitle: "",
        maxApplicant: "",
        currentApplicant: "",
        boardFinished: "",
      },
      room: {
        debateType: "",
      },
    };
  },
  computed: {
    ...mapState(["boards"]),
  },
  created() {
    this.boardAll();
  },
  methods: {
    ...mapActions(["BOARDALL"]),
    boardAll() {
      this.BOARDALL();
    },
    academyClick() {
      (this.academy = true), (this.free = false);
    },
    freeClick() {
      (this.academy = false), (this.free = true);
    },
    // 수정
    modalIn(num: any) {
      this.modal = true;
      num.modalNo;
    },
    modalOut() {
      this.modal = false;
    },
    typeAcademy(room: { debateType: string }, filter: string) {
      if (room.debateType === filter) {
        return true;
      }
      return false;
    },
    typeFree(room: { debateType: string }, filter: string) {
      if (room.debateType === filter) {
        return true;
      }
      return false;
    },
  },
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@400;700&display=swap");
* {
  margin: 0;
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
</style>
