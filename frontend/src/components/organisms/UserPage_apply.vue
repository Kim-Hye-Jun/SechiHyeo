<template>
  <div
    class="userpage-content-room"
    v-for="applicant_apply in applicant_apply_array"
    :key="applicant_apply"
  >
    <div class="userpage-content-count">
      {{ applicant_apply["current_applicant"] }}/{{
        applicant_apply["max_applicant"]
      }}
    </div>
    <div class="userpage-content-topic">
      {{ applicant_apply["debate_topic"] }}
    </div>
    <!-- 토론방 이동 메서드 추후에 토론방 완성 후 작성 예정... -->
    <div class="userpage-content-room-button">
      <a
        href="#"
        class="userpage-content-room-button-no"
        v-if="applicant_apply.accept === 0"
        >거절</a
      >
      <a
        href="#"
        class="userpage-content-room-button-start"
        v-if="applicant_apply.accept === 1"
        @click="moveToRoom(applicant_apply.debate_topic)"
        >입장</a
      >
      <!-- @click="moveToRoom(board['board_no'])" -->
      <a
        href="#"
        class="userpage-content-room-button-wait"
        v-if="applicant_apply.accept === 2"
        >대기</a
      >
    </div>
  </div>
  <!-- <div class="userpage-content-room">
    <div class="userpage-content-count">6/6</div>
    <div class="userpage-content-topic">나는 아무도 아니야</div>
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
  </div> -->
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions, mapState } from "vuex";
export default defineComponent({
  data() {
    return {
      // boards: [],
      // board: {
      //   board_no: 0,
      //   debate_topic: "",
      //   max_applicant: 0,
      //   current_applicant: 0,
      //   board_finished: "",
      // },
      // applicant_state: {
      //   login_id: "", // 추가 요청?
      //   board_no: 0, // 추가 요청?
      //   accept: 0,
      // },
    };
  },
  computed: {
    ...mapState([
      "boards",
      "debate_board",
      "applicant_apply",
      "applicant_apply_array",
      "memberinfo",
    ]),
  },
  created() {
    this.debateApply();
  },
  methods: {
    ...mapActions(["DEBATEAPPLY", "MEMBERINFO"]),
    async debateApply() {
      await this.DEBATEAPPLY();
    },
    moveToRoom(num: number) {
      console.log();
      this.$router.push("/room/" + num);
    },
    myApply(filter: any[]) {
      if (this.memberinfo.memberNo === filter[0]) {
        if (this.debate_board.debate_topic === filter[1]) return true;
        return false;
      }
      return false;
    },
    // consolelog(
    //   console.log(memberinfo.nickname);
    // )
  },
});
</script>

<style scoped>
.userpage-content-room {
  position: relative;
  width: 150px;
  height: 150px;
  margin: 16px;
  left: 30px;
  display: inline-block;
  justify-content: center;
  align-items: center;
  background: #757f9a;
  box-shadow: 0 0 0.5px 1px rgba(200, 230, 255, 0.5),
    0 -0.5px 0.5px 1.5px rgba(200, 230, 255, 0.5) inset,
    0 0 0.5px 2.5px rgba(135, 131, 171, 0.9),
    0 0 0.5px 1.75px rgba(135, 131, 171, 0.9) inset,
    0 0 3px 3.25px rgba(123, 108, 196, 0.9),
    0 0 3px 2.75px rgba(123, 108, 196, 0.9) inset,
    0 2px 7.5px 4.5px rgba(0, 0, 0, 0.8),
    0 2px 6.25px 3px rgba(0, 0, 0, 0.7) inset;
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
  font-weight: bold;
  font-size: 14px;
  line-height: 44px;
  color: rgba(255, 255, 255);
  /* text-shadow: 0 0 5px rgba(255, 245, 0, 1), 0 0 28px rgba(255, 44, 0, 0.9),
    0 8px 38px black, 0 8px 10px black, 0 2px 12px black,
    0 0 50px rgba(255, 120, 0, 0.5), 0 0 24px rgba(255, 255, 255, 0.8);
  -webkit-text-stroke: 1px rgba(255, 160, 0, 0.45); */
}
.userpage-content-count {
  position: absolute;
  right: 12px;
  bottom: -6px;
  font-family: "Inter";
  font-style: italic;
  font-weight: 500;
  font-size: 12px;
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
  font-size: 10px;
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
