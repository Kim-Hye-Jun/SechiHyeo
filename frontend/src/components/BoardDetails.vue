<template>
  <div class="modal-container">
    <header class="modal-container-header">
      <h1 class="modal-container-title">
        토론 주제 : {{ debate_board.debate_topic }}
      </h1>
    </header>
    <div class="modal-container-body article">
      <h3 class="board-master">작성자 : {{ debate_board.nickname }}</h3>
      <h3 class="board-day">토론 일시 : {{ debate_board.debate_time }}</h3>
      <h3 class="board-count">
        인원 : {{ debate_board.current_applicant }}/{{
          debate_board.max_applicant
        }}
      </h3>
      <h2 class="board-summary">개요</h2>
      <div class="board-summary-content">
        <li class="board-a">A 진영 : {{ debate_board.a_opinion }}</li>
        <li class="board-b">B 진영 : {{ debate_board.b_opinion }}</li>
        <p class="board-summary-in">{{ debate_board.board_content }}</p>
      </div>
      <div class="board-reply-box">
        <h3 class="board-reply">댓글</h3>
        <h5 class="board-reply-count">{{ reply.depth }} 개</h5>
      </div>
      <img class="board-reply-create-profile" :src="memberinfo.profileUrl" />
      <textarea class="board-reply-create-input" placeholder="댓글"></textarea>
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
        <button class="board-reply-ex-reply">답글</button>
        <img class="board-reply2-create-profile" :src="memberinfo.profileUrl" />
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
        </div>
      </div>
    </div>
    <footer class="modal-container-footer">
      <button class="button decline modalOut" @click="backPage">
        뒤로가기
      </button>
      <!-- 참가신청 버튼 메소드 미작성 -->
      <!-- <button class="button accept" @click="apply">참가신청</button> -->
      <button
        class="button accept"
        v-if="debate_board.userid !== memberinfo.user_id"
      >
        참가신청
      </button>
      <button
        class="button accept"
        v-if="debate_board.userid === memberinfo.user_id"
      >
        수정
      </button>
      <button
        class="button accept"
        v-if="debate_board.userid === memberinfo.user_id"
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
export default defineComponent({
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
    ...mapState(["boards", "debate_board", "memberinfo"]),
  },
  created() {
    let board_no = this.$route.params.board_no;
    // this.board_no = this.$route.params.board_no;
    this.boardOne(board_no as any as number);

    // this.boardOne(this.debate_board);
    // this.replyAll();
  },
  methods: {
    ...mapActions(["BOARDONE"]),
    // modalOut() {
    //   this.modal = false;
    //   this.$emit("modalOut");
    // },
    async boardOne(board_no: number) {
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
  },
});
</script>

<style></style>
