<template>
  <div id="debateRuleContainer">
    <p class="debateOrder">{{ turnUser }}</p>
    <p class="timer">{{ minutes }} : {{ seconds }}</p>
    <div id="timerBtn">
      <button-component
        @click="sendStartTimer"
        v-if="store.state.isRoomHost"
        buttonName="시작"
      ></button-component>
      <button-component
        v-if="store.state.isRoomHost"
        @click="sendStopTimer"
        buttonName="정지"
      ></button-component>
      <!-- <button @click="resetTimer">종료</button> -->
      <!-- <button @click="editTimer">시간 설정</button> -->
      <!-- <div v-if="edit">
        <input
          type="text"
          v-model="inputMin"
          placeholder="분"
          style="width: 40px"
        />
        <input
          type="text"
          v-model="inputSec"
          placeholder="초"
          style="width: 40px"
        />
      </div> -->
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
import ButtonComponent from "@components/atoms/common/ButtonComponent.vue";
import { member2 } from "@/api/index";

export default defineComponent({
  components: {
    ButtonComponent,
  },
  setup() {
    const store = useStore();
    return { store };
  },
  props: {
    roomAndUserData: Object,
  },
  mounted() {
    console.log("session mounted timer :", this.store.state.session);
    this.store.state.session?.on("signal:countdown-start", (event: any) => {
      this.startTimer();
    });
    this.store.state.session?.on("signal:countdown-stop", (event: any) => {
      this.stopTimer();
    });
    this.settings();
  },
  data() {
    return {
      //룰
      rule: [
        // 룰은 하드코딩 or 서버에서 받아오기? JSON 쉽지 않을듯
        ["a", "1", "입론", "10"], //360
        ["b", "3", "교차조사", "10"], //180
        ["b", "1", "입론", "10"], //360
        ["a", "3", "교차조사", "10"], //180
        ["a", "2", "반론", "10"], //300
        ["b", "1", "교차조사", "10"], //180
        ["b", "2", "반론", "10"], //300
        ["a", "1", "교차조사", "10"], //180
        ["a", "3", "결론", "10"], //300
        ["b", "3", "결론", "10"], //300
      ],
      // 1:1
      LincolnDouglas: [
        ["a", "1", "입론", "360"],
        ["b", "1", "교차조사", "180"],
        ["b", "1", "입론 및 반론", "420"],
        ["a", "1", "교차 조사", "180"],
        ["a", "1", "반론", "240"],
        ["b", "1", "결론", "360"],
        ["a", "1", "결론", "180"],
      ],
      // 2:2
      CEDA: [
        ["a", "1", "입론", "360"],
        ["b", "2", "교차 조사", "180"],
        ["b", "1", "입론", "360"],
        ["a", "1", "교차 조사", "180"],
        ["a", "2", "입론", "360"],
        ["b", "1", "교차 조사", "180"],
        ["b", "2", "입론", "360"],
        ["a", "2", "교차조사", "180"],
        ["b", "1", "반론", "240"],
        ["a", "1", "반론", "240"],
        ["b", "2", "결론", "240"],
        ["a", "2", "결론", "240"],
      ],
      // 3:3
      KarlPopper: [
        ["a", "1", "입론", "360"], //360
        ["b", "3", "교차조사", "180"], //180
        ["b", "1", "입론", "360"], //360
        ["a", "3", "교차조사", "180"], //180
        ["a", "2", "반론", "300"], //300
        ["b", "1", "교차조사", "180"], //180
        ["b", "2", "반론", "300"], //300
        ["a", "1", "교차조사", "180"], //180
        ["a", "3", "결론", "300"], //300
        ["b", "3", "결론", "300"], //300
      ],

      // 현재 턴
      turn: 0,
      turnUser: "현재 발표자 : " + "A1 " + "입론",
      isBreakTime: false,
      // 타이머
      timer: null as any,
      // inputMin: "",
      // inputSec: "",
      time: 360, // props로 받아야할듯? store나
      breakTime: 20, //쉬는 시간 길이
      resetButton: false,
      edit: false,
    };
  },
  computed: {
    minutes(): string {
      const minutes = Math.floor(this.time / 60);
      return this.padTime(minutes);
    },
    seconds(): string {
      const seconds = this.time - Number(this.minutes) * 60;
      return this.padTime(seconds);
    },
  },
  watch: {
    time: function (newTime, oldTime) {
      if (newTime == 0) {
        if (this.turn < this.rule.length) {
          if (!this.isBreakTime) {
            this.isBreakTime = true;
            this.turn++;
            if (this.turn == this.rule.length) {
              this.isBreakTime = false;
              this.turnUser = "토론이 종료되었습니다!! ";
              this.resetTimer();
              this.time = 0;
              // *** 토론 종료 서버에 요청
              member2.put(`/sessions/${this.store.state.roomId}/end`);
              console.log("종료");
              return;
            }
            this.turnUser =
              "쉬는시간" +
              " 다음 발표자 : " +
              this.rule[this.turn][0] +
              this.rule[this.turn][1] +
              this.rule[this.turn][2];
            this.resetTimer();
            this.time = this.breakTime; // 쉬는 시간
            this.startTimer();
          } else {
            this.isBreakTime = false;
            this.turnUser =
              "현재 발표자 : " +
              this.rule[this.turn][0] +
              this.rule[this.turn][1] +
              this.rule[this.turn][2];
            this.resetTimer();
            this.time = Number(this.rule[this.turn][3]);
            this.startTimer();
          }
        }
      }
      return;
    },
  },

  methods: {
    settings() {
      // 방 정보로 세팅해주기
      this.rule = this.KarlPopper;
      switch (this.roomAndUserData?.maxNumOfPeople) {
        case 2:
          this.rule = this.LincolnDouglas;
          break;
        case 4:
          this.rule = this.CEDA;
          break;
        case 6:
          this.rule = this.KarlPopper;
          break;
      }
      this.time = Number(this.rule[0][3]);
      this.turnUser =
        "현재 발표자 : " +
        this.rule[0][0] +
        " " +
        this.rule[0][1] +
        " " +
        this.rule[0][2];
      // this.breakTime = 20;
    },
    sendStartTimer() {
      this.store.state.session?.signal({
        data: "hello",
        to: [],
        type: "countdown-start",
      });
    },
    sendStopTimer() {
      this.store.state.session?.signal({
        data: "hello",
        to: [],
        type: "countdown-stop",
      });
    },
    startTimer() {
      this.timer = setInterval(() => this.countdown(), 1000);
      this.resetButton = true;
      this.edit = false;
    },
    stopTimer: function () {
      clearInterval(this.timer);
      this.timer = null;
      this.resetButton = true;
    },
    resetTimer: function () {
      this.time = 0;
      clearInterval(this.timer);
      this.timer = null;
      this.resetButton = false;
      // this.inputMin = "";
      // this.inputSec = "";
    },
    editTimer: function () {
      this.edit = !this.edit;
    },
    padTime: function (time: number) {
      return (time < 10 ? "0" : "") + time;
    },
    countdown: function () {
      if (this.time > 0) {
        this.time--;
      } else {
        this.resetTimer();
      }
    },
  },
});
</script>
<style scoped>
#debateRuleContainer {
  display: flex;
  align-items: center;
  flex-direction: row;

  background: radial-gradient(circle, #0f101a 0%, #222858 100%);
  border-radius: 6px;

  width: 80%;
  margin-bottom: 20px;
}
.debateOrder {
  font-size: 30px;
  color: white;
  padding-top: 5px;
  padding-left: 15%;
  padding-right: 5%;
}
.timer {
  width: 30%;
  font-size: 60px;
  color: white;
}
#timerBtn {
  margin: 3px;
}
</style>
