<template>
  <div class="userpage-content-intro-text">
    토론이 삶이다! {{ memberinfo?.introduce }}
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions, mapState } from "vuex";
export default defineComponent({
  data() {
    return {};
  },
  computed: {
    ...mapState(["memberinfo"]),
  },
  created() {
    this.memberProfile();
  },
  methods: {
    ...mapActions(["MEMBERPROFILE"]),
    memberProfile() {
      this.MEMBERPROFILE();
    },
  },
});
</script>

<style scoped>
.userpage-content-intro-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-family: "Inter";
  font-style: normal;
  font-weight: bolder;
  font-size: 40px;
  line-height: 44px;
  text-align: center;
  color: #ffffff;
}
</style>
