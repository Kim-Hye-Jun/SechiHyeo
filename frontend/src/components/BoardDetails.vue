<template>
  <Background></Background>
  <div class="modal-container">
    <header class="modal-container-header">
      <h1 class="modal-container-title">
        {{ debate_board.board.debate_topic }}
      </h1>
    </header>
    <div class="modal-container-body">
      <h3 class="board-master">작성자 : {{ debate_board.board.nickname }}</h3>
      <br />
      <h3 class="board-day">
        토론 일시 : {{ Unix_timestamp(debate_board.board.debate_time) }}
      </h3>
      <br />
      <h3 class="board-count">
        인원 :
        <!-- {{ debate_board.board.current_applicant }}/ -->
        {{ debate_board.board.max_applicant }} 명
      </h3>
      <hr />
      <br />
      <!-- <h2 class="board-summary">개요</h2> -->
      <div class="board-summary-content">
        <li class="board-a">A 진영 : {{ debate_board.board.a_opinion }}</li>
        <li class="board-b">B 진영 : {{ debate_board.board.b_opinion }}</li>
        <hr />
        <br />
        <div class="board-summary-in">
          {{ debate_board.board.board_content }}
        </div>
        <hr />
      </div>
      <!-- <div class="wrapper">
        <h4 style="display: inline-block">A</h4>
        <input type="checkbox" id="switch" />
        <label for="switch" class="switch_label">
          <span class="onf_btn"></span>
        </label>
        <h4 style="display: inline-block; margin-left: 10px">B</h4>
      </div> -->
      <div style="margin-top: 10px">
        <label style="margin-right: 20px"
          ><input type="radio" v-model="applicant.side" value="0" /> A
          진영</label
        >
        <label
          ><input type="radio" v-model="applicant.side" value="1" /> B
          진영</label
        >
      </div>
      <br />
      <div>
        <label style="margin-right: 23px"
          ><input type="radio" v-model="applicant.order" value="1" /> 순서
          1</label
        >
        <label style="margin-right: 23px"
          ><input type="radio" v-model="applicant.order" value="2" /> 순서
          2</label
        >
        <label
          ><input type="radio" v-model="applicant.order" value="3" /> 순서
          3</label
        >
        <!-- <p>
          <input type="submit" value="Submit" />
          <input type="reset" value="Reset" />
        </p> -->
      </div>
      <hr />
      <div class="board-reply-box">
        <h3 class="board-reply">댓글</h3>
        <!-- <h5 class="board-reply-count">{{ 0 }} 개</h5> -->
        <!-- 댓글 개수 미구현 -->
      </div>
      <input class="board-reply-create-input" placeholder="댓글" />
      <button class="board-reply-create-sign" @click="replyInsert()">
        등록
      </button>
      <!-- 댓글 반복 -->
      <div v-for="reply in replies" :key="reply['reply_no']">
        <img class="board-reply-ex-profile" :src="reply['profile_url']" />
        <p class="board-reply-ex-user">User {{ reply["nickname"] }}</p>
        <p class="board-reply-ex-user-content">댓글 {{ reply["context"] }}</p>
        <button
          class="board-reply-ex-delete"
          @click="replyDelete(reply['reply_no'])"
        >
          삭제
        </button>
        <button
          class="board-reply-ex-update"
          @click="replyUpdate(reply['reply_no'])"
        >
          수정
        </button>
        <!-- 대댓글 토글 버튼 -->
        <!-- <button class="board-reply-ex-reply">답글</button>
        <textarea
          class="board-reply2-create-input"
          placeholder="대댓글"
        ></textarea>
        <button class="board-reply2-create-sign" @click="replyInsert()">
          등록
        </button> -->
        <!-- 대댓글 반복 -->
        <!-- <div
          class="board-reply2-ex"
          v-for="reply in replies"
          :key="reply"
          :filter="reply['reply_no']"
          :filter-function="reReply(reply['parent_no'], reply['reply_no'])"
        >
          <image class="board-reply2-ex-profile" :src="reply['profile_url']" />
          <p class="board-reply2-ex-user">User {{ reply["nickname"] }}</p>
          <p>대댓글 {{ reply["context"] }}</p>
          <button
            class="board-reply2-ex-delete"
            @click="replyDelete(reply['reply_no'])"
          >
            삭제
          </button>
          <button
            class="board-reply2-ex-update"
            @click="replyUpdate(reply['reply_no'])"
          >
            수정
          </button>
        </div> -->
      </div>
    </div>
    <footer class="modal-container-footer">
      <button class="button" @click="backPage">뒤로가기</button>
      <!-- 참가신청 버튼 메소드 미작성 -->
      <!-- <button class="button accept" @click="apply">참가신청</button> -->
      <button
        class="button apply"
        @click="postApply()"
        v-if="debate_board.board.nickname !== memberinfo.nickname"
      >
        참가신청
      </button>
      <button
        class="button rema"
        v-if="debate_board.board.nickname === memberinfo.nickname"
      >
        수정
      </button>
      <button
        class="button del"
        v-if="debate_board.board.nickname === memberinfo.nickname"
        @click="boardDelete(debate_board.board.board_no)"
      >
        삭제
      </button>
    </footer>
  </div>
</template>

<script lang="ts">
import axios from "axios";
import { defineComponent } from "vue";
import { mapActions, mapState, useStore } from "vuex";
import Background from "@/components/common/Background.vue";
export default defineComponent({
  components: { Background },
  setup() {
    const store = useStore();
    return { store };
  },
  data() {
    return {
      // modal: true,
      // boards: [],
      // board: {},
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
    ...mapState(["boards", "board", "debate_board", "memberinfo", "applicant"]),
  },
  // created() {
  //   let board_no = this.$route.params.board_no;
  //   // this.board_no = this.$route.params.board_no;
  //   this.boardOne(board_no as any as number);

  //   // this.boardOne(this.debate_board);
  //   // this.replyAll();
  // },
  methods: {
    ...mapActions(["BOARDONE", "BOARDDELETE", "POSTAPPLY"]),
    // modalOut() {
    //   this.modal = false;
    //   this.$emit("modalOut");
    // },
    async boardOne(board_no: number) {
      console.log(board_no);
      // let no = board_no;
      this.BOARDONE(board_no);
      // let boardNum = this.debate_board.board_no;
      // axios
      //   .get("https://i7a508.p.ssafy.io/api/debate-board/", {
      //     headers: {
      //       "access-token": this.store.state.token,
      //     },
      //   })
      //   .then((res) => {
      //     this.store.commit("BOARDONE", {
      //       board: res.data,
      //     });
      //   });
      // console.log(boardNum);
    },
    async boardDelete(num: number) {
      console.log(num);
      // console.log(debate_board.board.board_no);
      this.BOARDDELETE(num);
    },
    postApply() {
      this.POSTAPPLY({
        board_no: this.debate_board.board.board_no,
        order: this.applicant.order,
        side: this.applicant.side,
      });
      console.log(this.debate_board.board.board_no);
      console.log(this.applicant.order);
      console.log(this.applicant.side);
    },
    // 자식 대댓글만을 걸러내기 위한 부모 댓글 필터
    reReply(reply: any, filter: number) {
      if (reply.parent_no === filter) {
        return true;
      }
      return false;
    },
    // replyAll() {
    //   axios
    //     .get(
    //       "https://i7a508.p.ssafy.io/api/debate-board/" + this.board.board_no
    //     )
    //     .then((res) => {
    //       this.replies = res.data;
    //     });
    // },
    replyInsert() {
      axios
        .post(
          "https://i7a508.p.ssafy.io/api/debate-reply/" +
            this.debate_board.board_no,
          {
            parent_no: this.reply.parent_no,
            context: this.reply.context,
            depth: this.reply.depth,
          }
        )
        .then(() => {
          this.BOARDONE();
        });
    },
    replyUpdate(reply_no: number) {
      axios
        .put(`https://i7a508.p.ssafy.io/api/debate-reply/`, reply_no)
        .then((res) => {
          console.log(res.data);
        });
    },
    replyDelete(reply_no: number) {
      let flag = confirm("정말로 삭제하시겠습니까??");
      if (flag) {
        axios
          .delete("https://i7a508.p.ssafy.io/api/debate-reply/" + reply_no)
          .then(() => {
            this.BOARDONE();
          });
      }
    },
    backPage() {
      this.$router.go(-1);
    },
    Unix_timestamp(t: any) {
      var date = new Date(t);
      var year = date.getFullYear();
      var month = "0" + (date.getMonth() + 1);
      var day = "0" + date.getDate();
      var hour = "0" + date.getHours();
      var minute = "0" + date.getMinutes();
      var second = "0" + date.getSeconds();
      return (
        year +
        "-" +
        month.substr(-2) +
        "-" +
        day.substr(-2) +
        " " +
        hour.substr(-2) +
        ":" +
        minute.substr(-2) +
        ":" +
        second.substr(-2)
      );
    },
    con() {
      console.log(this.Unix_timestamp(1660706175750));
    },
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
  font-family: "locus_sangsang";
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
.modal-container {
  width: 800px;
  margin-top: 100px;
  margin-left: 50%;
  transform: translate(-50%);
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
  /* padding-top: 12px; */
  padding: 16px 32px;
  border-bottom: 2px solid rgb(0, 32, 68);
  background: #d7dde8;
  display: flex;
  align-items: center;
  justify-content: center;
}
.modal-container-title {
  display: flex;
  /* align-items: center; */
  gap: 8px;
  line-height: 1;
  /* font-family: "Times New Roman", Times, serif; */
  /* font-weight: bold; */
  font-size: 1;
  color: #000000;
  margin-top: 5px;
}
.modal-container-body {
  background: #d7dde8;
  /* margin-left: 30px; */
  padding: 24px 32px 48px;
  /* font-family: serif; */
  overflow-y: auto;
  color: #000000;
}
.modal-container-footer {
  background: #d7dde8;
  padding: 20px 32px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  border-top: 2px solid rgb(0, 32, 68);
  gap: 12px;
  position: relative;
}
.button {
  padding: 12px 20px;
  border-radius: 8px;
  background-color: transparent;
  color: #000000;
  border: 0;
  /* font-weight: 600; */
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
  left: 0px;
  width: 240px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: bold; */
  font-size: 20px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  /* border-bottom: 1px solid #ddd; */
  margin-top: 10px;
}
.board-day {
  position: relative;
  display: inline-block;
  top: -20px;
  width: 320px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 20px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  /* border-bottom: 1px solid #ddd; */
}
.board-count {
  position: relative;
  display: inline-block;
  top: -20px;
  /* right: -50px; */
  width: 100px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 20px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  /* border-bottom: 1px solid #ddd; */
}
.board-summary {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 240px;
  top: 0px;
  left: 0px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 800; */
  font-size: 20px;
  color: #000000;
  /* border-right: 3px solid #ddd; */
}
.board-summary-content {
  position: relative;
  display: inline-block;
  width: 450px;
  height: 200px;
  list-style: none;
}
.board-a {
  position: relative;
  /* display: inline-block; */
  width: 400px;
  padding-left: 20px;
  top: -30px;
  left: 0px;
  font-style: initial;
  /* font-weight: bold; */
  font-size: 20px;
  text-align: left;
  line-height: 34px;
  color: #000000;
}
.board-b {
  position: relative;
  /* display: inline-block; */
  width: 400px;
  padding-left: 20px;
  top: -30px;
  left: 0px;
  font-style: normal;
  /* font-weight: bold; */
  font-size: 20px;
  text-align: left;
  line-height: 34px;
  color: #000000;
  /* border-bottom: 3px solid #ddd; */
}
.board-summary-in {
  position: relative;
  /* display: inline-block; */
  width: 400px;
  /* height: 100px; */
  top: -20px;
  left: 10px;
  text-align: left;
  /* font-weight: bold; */
  font-size: larger;
}
.board-reply-box {
  position: relative;
  display: inline-block;
  width: 580px;
  /* border-bottom: 3px solid #ddd; */
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
  top: 15px;
  color: #000000;
  padding: 5px;
  /* background-color: #111845; */
  transition: 0.3s;
  border-radius: 5px;
  border: 0px;
  border-bottom: 1px solid #ffffff;
}
.board-reply-create-input:focus {
  background-color: #111845;
  color: white;
}
.board-reply-create-sign {
  position: relative;
  width: 50px;
  height: 30px;
  left: 10px;
  top: 18px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 15px;
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
  /* box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 100px #03e9f4; */
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
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.board-reply-ex-delete {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  left: 0px;
  top: -20px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.board-reply-ex-reply {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  left: 200px;
  top: -20px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.board-reply-ex-user {
  position: relative;
  display: inline-block;
  width: 56px;
  height: 30px;
  top: 60px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
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
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.board-reply2-create-cancle {
  position: relative;
  display: inline-block;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.board-reply2-create-sign {
  position: relative;
  display: inline-block;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
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
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.board-reply2-ex-delete {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 30px;
  top: 21px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
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
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 12px;
  line-height: 30px;
  color: #000000;
}
.wrapper {
  width: 50px;
  height: 50px;
  text-align: center;
  margin-left: 600px;
}
#switch {
  position: absolute;
  /* hidden */
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}

.switch_label {
  position: absolute;
  cursor: pointer;
  display: inline-block;
  width: 58px;
  height: 28px;
  background: #c44;
  border: 2px solid #daa;
  border-radius: 20px;
  transition: 0.2s;
}
.switch_label:hover {
  background: #efefef;
}
.onf_btn {
  position: absolute;
  top: 4px;
  left: 3px;
  display: inline-block;
  width: 20px;
  height: 20px;
  border-radius: 20px;
  background: #daa;
  transition: 0.2s;
}

/* checking style */
#switch:checked + .switch_label {
  background: #c44;
  border: 2px solid #c44;
}

#switch:checked + .switch_label:hover {
  background: #e55;
}

/* move */
#switch:checked + .switch_label .onf_btn {
  left: 34px;
  background: #fff;
  box-shadow: 1px 2px 3px #00000020;
}
</style>
