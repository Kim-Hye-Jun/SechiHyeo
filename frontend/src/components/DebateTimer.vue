<template>
  <div id="session-timer" style="margin-left: 30%">
    <div>
      <h3>{{ turn }} : {{ minutes }} : {{ seconds }}</h3>
    </div>
    <div id="timerBtn">
      <button @click="sendStartTimer">시작</button>
      <button @click="sendStopTimer">정지</button>
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
export default defineComponent({
  setup() {
    const store = useStore();

    return { store };
  },
  mounted() {
    this.store.state.session.on("signal:countdown-start", (event: any) => {
      this.startTimer();
    });
    this.store.state.session.on("signal:countdown-stop", (event: any) => {
      this.stopTimer();
    });
  },
  data() {
    return {
      // 턴
      turn: "A1",
      // 타이머
      timer: setInterval(() => {
        return 10;
      }, 100000),
      inputMin: "",
      inputSec: "",
      time: 120,
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

  methods: {
    sendStartTimer() {
      this.store.state.session.signal({
        data: "hello",
        to: [],
        type: "countdown-start",
      });
    },
    sendStopTimer() {
      this.store.state.session.signal({
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
      this.timer = setInterval(() => {
        return 10;
      }, 100000);
      this.resetButton = true;
    },
    resetTimer: function () {
      this.time = 0;
      clearInterval(this.timer);
      this.timer = setInterval(() => {
        return 10;
      }, 100000);
      this.resetButton = false;
      this.inputMin = "";
      this.inputSec = "";
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
<style scoped></style>
