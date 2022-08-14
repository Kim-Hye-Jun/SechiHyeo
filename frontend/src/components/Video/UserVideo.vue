<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <div>
      <p>{{ clientData + "이게 이름이구나" }}</p>
    </div>
  </div>
</template>

<script>
import OvVideo from "@/components/Video/OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      // console.log("스트림 출력");
      // console.log(this.streamManager.stream);
      // console.log("스트림 출력 종료");
      return JSON.parse(connection.data);
    },
  },
};
</script>
