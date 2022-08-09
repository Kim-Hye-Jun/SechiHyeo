<template>
  <div
    class="userpage-content-room"
    v-for="board in boards"
    :key="board"
    :filter="user.id"
    :filter-function="myApply"
  >
    <div class="userpage-content-count">{{ board.maxApplicant }}/6</div>
    <div class="userpage-content-topic">{{ board.title }}토론의 시대다</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start" @click="moveToRoom"
        >입장</a
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
import { mapState } from "vuex";
export default {
  data() {
    return {
      boards: [],
      board: {
        num: "",
        title: "",
        count: "",
      },
    };
  },
  // created() {
  //   this.boardAll();
  // },
  computed: {
    ...mapState("boardStore", ["board"]),
  },
  methods: {
    // moveToRoom(board: { num: string }) {
    //   this.$router.push("/room/" + board.num);
    // },
    myApply(user: { id: any }, filter: any) {
      if (user.id === filter) {
        return true;
      }
      return false;
    },
  },
};
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
