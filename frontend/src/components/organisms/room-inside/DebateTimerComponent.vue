<template>
  <div id="debateRuleContainer">
    <p class="debateOrder">{{ turnUser }}</p>
    <p class="timer">{{ minutes }} : {{ seconds }}</p>
    <div id="timerBtn">
      <button-component
        @click="sendStartTimer"
        buttonName="시작"
      ></button-component>
      <button-component
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
import ButtonComponent from "@components/atoms/common/ButtonComponent.vue";

export default defineComponent({
  components: {
    ButtonComponent,
  },
  props: {
    session: Object,
  },
  mounted() {
    this.session?.on("signal:countdown-start", (event: any) => {
      this.startTimer();
    });
    this.session?.on("signal:countdown-stop", (event: any) => {
      this.stopTimer();
    });
    // this.settings();
  },
  data() {
    return {
      //룰
      rule: [
        // 룰은 하드코딩 or 서버에서 받아오기? JSON 쉽지 않을듯
        ["A", "1", "입론", "5"], //360
        ["B", "3", "교차조사", "5"], //180
        ["B", "1", "입론", "5"], //360
        ["A", "3", "교차조사", "5"], //180
        ["A", "2", "반론", "5"], //300
        ["B", "1", "교차조사", "5"], //180
        ["B", "2", "반론", "5"], //300
        ["A", "1", "교차조사", "5"], //180
        ["A", "3", "결론", "5"], //300
        ["B", "3", "결론", "5"], //300
      ],
      // 1:1
      LincolnDouglas: [
        ["A", "1", "입론", "360"],
        ["B", "1", "교차조사", "180"],
        ["B", "1", "입론 및 반론", "420"],
        ["A", "1", "교차 조사", "180"],
        ["A", "1", "반론", "240"],
        ["B", "1", "결론", "360"],
        ["A", "1", "결론", "180"],
      ],
      // 2:2
      CEDA: [
        ["A", "1", "입론", "360"],
        ["B", "2", "교차 조사", "180"],
        ["B", "1", "입론", "360"],
        ["A", "1", "교차 조사", "180"],
        ["A", "2", "입론", "360"],
        ["B", "1", "교차 조사", "180"],
        ["B", "2", "입론", "360"],
        ["A", "2", "교차조사", "180"],
        ["B", "1", "반론", "240"],
        ["A", "1", "반론", "240"],
        ["B", "2", "결론", "240"],
        ["A", "2", "결론", "240"],
      ],
      // 3:3
      KarlPopper: [
        ["A", "1", "입론", "360"], //360
        ["B", "3", "교차조사", "180"], //180
        ["B", "1", "입론", "360"], //360
        ["A", "3", "교차조사", "180"], //180
        ["A", "2", "반론", "300"], //300
        ["B", "1", "교차조사", "180"], //180
        ["B", "2", "반론", "300"], //300
        ["A", "1", "교차조사", "180"], //180
        ["A", "3", "결론", "300"], //300
        ["B", "3", "결론", "300"], //300
      ],

      // 현재 턴
      turn: 0,
      turnUser: "현재 발표자 : " + "A1 " + "입론",
      isBreakTime: false,
      // 타이머
      timer: null as any,
      // inputMin: "",
      // inputSec: "",
      time: 5, // props로 받아야할듯? store나
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
      this.session?.signal({
        data: "hello",
        to: [],
        type: "countdown-start",
      });
    },
    sendStopTimer() {
      this.session?.signal({
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
  flex-direction: column;

  background: radial-gradient(circle, #1a2049 0%, #13162f 100%);
  border-radius: 6px;

  width: 20%;
}
.debateOrder {
  padding: 3px;
  color: rgb(42, 204, 42);
}
.timer {
  padding: 3px;
  color: yellow;
}
#timerBtn {
  margin-top: 10px;
}
</style>
