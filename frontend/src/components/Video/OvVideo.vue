<template>
  <video autoplay />
</template>

<script>
export default {
  name: "OvVideo",
  props: {
    streamManager: Object,
  },

  watch: {
    streamManager: function () {
      this.streamManager.addVideoElement(this.$el);
    },
  },

  mounted() {
    //this.$el은 video 태그임
    //addVideoElement 함수로 넘겨받은 태그가 스트림매니저가 담당하는 스트림 출력하게 한다.
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
