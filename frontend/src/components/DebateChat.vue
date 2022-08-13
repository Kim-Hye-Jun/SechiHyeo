<template>
  <div>
    <div>
      <div>
        <span> 채팅 </span>
        <!-- <button class="btn close-btn" @click="toggleChatPanel">
          <i class="fas fa-times"></i>
        </button> -->
      </div>
      <!-- 채팅 내역 -->
      <div>
        <div v-for="(messageOne, i) of messages" :key="i">
          <div>
            <span>{{ messageOne.sender }}</span>
            <span>{{ messageOne.time }}</span>
          </div>
          <div>
            {{ messageOne.content }}
          </div>
        </div>
      </div>
      <div>
        <div>
          <input v-model="message" @keyup.enter="clickSendMessage" />
        </div>
        <div>
          <button type="button" @click="clickSendMessage">
            <i>메세지 전송</i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  name: "DebateChat",
  setup() {
    const store = useStore();

    return { store };
  },
  mounted() {
    this.store.state.session.on("signal:send-massage", (event: any) => {
      console.log(event.data);
      let message = JSON.parse(event.data);
      this.messages.push(message);
    });
  },
  data() {
    return {
      messages: [] as any[],
      // message: { sender: "", content: "", time: "" },
      message: "",
    };
  },
  watch: {
    // messages() {
    //   setTimeout(() => {
    //     var chatDiv = document.getElementById("chat-area");
    //     if (chatDiv != null) {
    //       chatDiv.scrollTo({
    //         top: chatDiv.scrollHeight - chatDiv.clientHeight,
    //         behavior: "smooth",
    //       });
    //     }
    //   }, 50);
    // },
  },
  computed: {},
  methods: {
    clickSendMessage() {
      if (this.message.trim()) {
        let sendMessage = {
          sender: "",
          content: "",
          time: "",
        };
        sendMessage.content = this.message;
        sendMessage.sender = this.store.state.memberinfo.nickname;
        const now = new Date();
        let hours = String("0" + now.getHours()).slice(-2);
        let minutes = String("0" + now.getMinutes()).slice(-2);
        let seconds = String("0" + now.getSeconds()).slice(-2);
        let time = hours + ":" + minutes + ":" + seconds;
        sendMessage.time = time;
        console.log(sendMessage);

        this.store.state.session.signal({
          data: JSON.stringify(sendMessage),
          to: [],
          type: "send-massage",
        });
        this.message = "";
      }
    },
  },
});
</script>
<style scoped></style>
