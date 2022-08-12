<template>
  <div
    class="userpage-content-room"
    v-for="board in boards"
    :key="board"
    :filter="(applicant.login_id, applicant.board_no)"
    :filter-function="myApply"
  >
    <div class="userpage-content-count">
      {{ board.current_applicant }}/{{ board.max_applicant }}
    </div>
    <div class="userpage-content-topic">
      {{ board.debate_topic }}토론의 시대
    </div>
    <!-- 토론방 이동 메서드 추후에 토론방 완성 후 작성 예정... -->
    <div class="userpage-content-room-button">
      <a
        href="#"
        class="userpage-content-room-button-no"
        v-if="applicant.accept === 0"
        >거절</a
      >
      <a
        href="#"
        class="userpage-content-room-button-start"
        @click="moveToRoom(board.board_no)"
        v-if="applicant.accept === 1"
        >입장</a
      >
      <a
        href="#"
        class="userpage-content-room-button-wait"
        v-if="applicant.accept === 2"
        >대기</a
      >
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">멈춰</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-wait">대기</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">나는 아무도 아니야</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-no">거부</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">오지 마</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-no">거부</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">어서 오세요.</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start">입장</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">들어오고 싶은가?</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-no">거부</a>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions, mapState } from "vuex";
export default defineComponent({
  data() {
    return {
      member: {
        member_no: 0,
        login_id: "",
      },
      boards: [],
      board: {
        board_no: 0,
        debate_topic: "",
        max_applicant: 0,
        current_applicant: 0,
        board_finished: "",
      } as object,
      applicant: {
        login_id: "",
        board_no: 0,
        accept: 0,
      },
    };
  },
  computed: {
    ...mapState(["board"]),
  },
  created() {
    this.debateApply();
  },
  methods: {
    ...mapActions(["DEBATEAPPLY"]),
    debateApply() {
      this.DEBATEAPPLY();
    },
    moveToRoom(board: { board_no: string }) {
      this.$router.push("/room/" + board.board_no);
    },
    myApply(
      member: { login_id: string },
      board: { board_no: number },
      filter: any[]
    ) {
      if (member.login_id === filter[0]) {
        if (board.board_no === filter[1]) return true;
        return false;
      }
      return false;
    },
  },
});
</script>

<style>
.userpage-content-apply {
  position: relative;
  width: 800px;
  height: 540px;
  top: 460px;
  left: 50%;
  transform: translate(-50%);
}
</style>
