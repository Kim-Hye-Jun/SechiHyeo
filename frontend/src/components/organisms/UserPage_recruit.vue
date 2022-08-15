<template>
  <div
    class="userpage-content-room"
    v-for="board in boards"
    :key="board"
    :filter="board['memberNo']"
    :filter-function="myRecruit"
  >
    <div class="userpage-content-count">
      {{ board["currentApplicant"] }}/{{ board["maxApplicant"] }}
    </div>
    <div class="userpage-content-topic">
      {{ board["debateTopic"] }}미래와 토론의 연관
    </div>
    <!-- 토론방 이동 메서드 추후에 토론방 완성 후 작성 예정... -->
    <div class="userpage-content-room-button">
      <a
        href="#"
        class="userpage-content-room-button-start"
        @click="moveToRoom(board)"
        >시작</a
      >
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">네온사인 불빛 아래</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start">시작</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">우주 쓰레기에 대해</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start">시작</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">우주문명에서 토론</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start">시작</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">이 주제는 영국</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start">시작</a>
    </div>
  </div>
  <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">자 토론을 시작하지</div>
    <div class="userpage-content-room-button">
      <a href="#" class="userpage-content-room-button-start">시작</a>
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
        memberNo: "",
      },
      boards: [],
      board: {
        debateTopic: "",
        maxApplicant: "",
        currentApplicant: "",
        boardFinished: "",
      },
    };
  },
  computed: {
    ...mapState(["board"]),
  },
  created() {
    this.debateRecruit();
  },
  methods: {
    ...mapActions(["DEBATERECRUIT"]),
    debateRecruit() {
      this.DEBATERECRUIT();
    },
    moveToRoom(board: { num: string }) {
      this.$router.push("/room/" + board.num);
    },
    myRecruit(member: { memberNo: any }, filter: any) {
      if (member.memberNo === filter) {
        return true;
      }
      return false;
    },
  },
});
</script>

<style>
.userpage-content-recruit {
  position: relative;
  width: 800px;
  height: 540px;
  top: 460px;
  left: 50%;
  transform: translate(-50%);
}
.userpage-content-room {
  position: relative;
  width: 200px;
  height: 200px;
  margin: 30px;
  display: inline-block;
  justify-content: center;
  align-items: center;
  background: #757f9a;
  box-shadow: 0 0 1px 2px rgba(200, 230, 255, 0.5),
    0 -1px 1px 3px rgba(200, 230, 255, 0.5) inset,
    0 0 1px 5px rgba(135, 131, 171, 0.9),
    0 0 1px 3.5px rgba(135, 131, 171, 0.9) inset,
    0 0 6px 6.5px rgba(123, 108, 196, 0.9),
    0 0 6px 5.5px rgba(123, 108, 196, 0.9) inset,
    0 4px 15px 9px rgba(0, 0, 0, 0.8), 0 4px 12.5px 6px rgba(0, 0, 0, 0.7) inset;
}
.userpage-content-box {
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  border-radius: 10px;
  display: none;
  width: 400px;
  height: 400px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 20px;
  background: #757f9a;
  box-shadow: 0 0 1px 2px rgba(200, 230, 255, 0.5),
    0 -1px 1px 3px rgba(200, 230, 255, 0.5) inset,
    0 0 1px 5px rgba(135, 131, 171, 0.9),
    0 0 1px 3.5px rgba(135, 131, 171, 0.9) inset,
    0 0 6px 6.5px rgba(123, 108, 196, 0.9),
    0 0 6px 5.5px rgba(123, 108, 196, 0.9) inset,
    0 4px 15px 9px rgba(0, 0, 0, 0.8), 0 4px 12.5px 6px rgba(0, 0, 0, 0.7) inset;
}
.userpage-content-topic {
  position: absolute;
  margin-left: 10px;
  margin-right: 10px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 500;
  font-size: 20px;
  line-height: 44px;
  color: rgba(255, 232, 50, 0.9);
  text-shadow: 0 0 5px rgba(255, 245, 0, 1), 0 0 28px rgba(255, 44, 0, 0.9),
    0 8px 38px black, 0 8px 10px black, 0 2px 12px black,
    0 0 50px rgba(255, 120, 0, 0.5), 0 0 24px rgba(255, 255, 255, 0.8);
  -webkit-text-stroke: 1px rgba(255, 160, 0, 0.45);
}
.userpage-content-count {
  position: absolute;
  right: 20px;
  bottom: 0px;
  font-family: "Inter";
  font-style: italic;
  font-weight: 500;
  font-size: 16px;
  line-height: 44px;
  text-align: center;
  color: #ffffff;
}
.userpage-content-room-button {
  position: absolute;
  top: 80%;
  left: 50%;
  transform: translate(-50%);
  font-family: "Inter";
  font-style: italic;
  font-weight: bolder;
  font-size: 16px;
  text-align: center;
}
.userpage-content-room-button-start {
  padding: 7px 14px;
  color: #03e9f4;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #03e9f4;
  outline: 0;
  border-radius: 5px;
  font-weight: bold;
}
.userpage-content-room-button-start:hover {
  background: #03e9f4;
  color: #fff;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 100px #03e9f4;
}
.userpage-content-room-button-no {
  padding: 7px 14px;
  color: #ae2626;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #ae2626;
  outline: 0;
  border-radius: 5px;
  font-weight: bold;
}
.userpage-content-room-button-no:hover {
  background: #ae2626;
  color: #fff;
  box-shadow: 0 0 5px #ae2626, 0 0 25px #ae2626, 0 0 50px #ae2626,
    0 0 100px #ae2626;
}
.userpage-content-room-button-wait {
  padding: 7px 14px;
  color: #ffe53b;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #ffe53b;
  outline: 0;
  border-radius: 5px;
  font-weight: bold;
}
.userpage-content-room-button-wait:hover {
  background: #ffe53b;
  color: #fff;
  box-shadow: 0 0 5px #ffe53b, 0 0 25px #ffe53b, 0 0 50px #ffe53b,
    0 0 100px #ffe53b;
}
.userpage-content-room-button-apply {
  padding: 7px 14px;
  color: #03f46f;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #03f46f;
  outline: 0;
  border-radius: 15px;
  font-weight: bold;
}
.userpage-content-room-button-apply:hover {
  background: #03f46f;
  color: #fff;
  box-shadow: 0 0 5px #03f46f, 0 0 25px #03f46f, 0 0 50px #03f46f,
    0 0 100px #03f46f;
}
</style>
