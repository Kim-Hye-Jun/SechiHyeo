<template>
  <div class="userpage-content-intro-text">
    토론이 삶이다! {{ member.introduce }}
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions } from "vuex";
export default defineComponent({
  data() {
    return {
      member: {
        introduce: "",
      },
    };
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

<style>
.userpage-content-intro {
  position: relative;
  top: 460px;
  left: 50%;
  transform: translate(-50%);
  width: 800px;
  height: 200px;
  background: #757f9a;
  box-shadow: 0 0 1px 2px rgba(200, 230, 255, 0.5),
    0 -1px 1px 3px rgba(200, 230, 255, 0.5) inset,
    0 0 1px 5px rgba(135, 131, 171, 0.9),
    0 0 1px 3.5px rgba(135, 131, 171, 0.9) inset,
    0 0 6px 6.5px rgba(123, 108, 196, 0.9),
    0 0 6px 5.5px rgba(123, 108, 196, 0.9) inset,
    0 4px 15px 9px rgba(0, 0, 0, 0.8), 0 4px 12.5px 6px rgba(0, 0, 0, 0.7) inset;
  border: 0px;
}
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
