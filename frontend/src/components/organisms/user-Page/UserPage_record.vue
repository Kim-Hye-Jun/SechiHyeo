<template>
  <div class="userpage-content-record-time-box">
    {{ memberinfo?.debateNumber }} Fight Record
  </div>
  <div class="userpage-content-intro-text">
    토론이 삶이다! {{ memberinfo?.introduce }}
  </div>
  <!-- <div class="userpage-content-record-inform-box">
    <div class="userpage-content-record-inform">
      {{ memberinfo?.recordWin }}
    </div>
    <div class="userpage-content-record-inform">
      {{ memberinfo?.recordLose }}
    </div>
    <div class="userpage-content-record-inform">
      {{ memberinfo?.recordDraw }}
    </div>
    <div class="userpage-content-record-pagination">1 2 3 4 5 6</div>
  </div> -->
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
    this.memberRecord();
    this.memberProfile();
  },
  methods: {
    ...mapActions(["MEMBERPROFILE", "MEMBERPROFILE"]),
    memberRecord() {
      this.MEMBERPROFILE();
    },
    memberProfile() {
      this.MEMBERPROFILE();
    },
  },
});
</script>

<style scoped>
* {
  font-family: "locus_sangsang";
}
.userpage-content-record-time-box {
  position: relative;
  width: 580px;
  left: 50%;
  transform: translate(-50%);
  top: 20px;
  font-weight: bold;
  /* font-family: "Montserrat", sans-serif; */
  font-style: normal;
  /* font-weight: bold; */
  font-size: 30px;
  text-align: center;
  color: #070707;
  background: #757f9a;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 2px;
  outline: 0;
  border-radius: 5px;
  text-align: center;
  letter-spacing: 0px;
  transition: 1s;
  -webkit-transition: 1s;
  -ms-transition: 1s;
  padding: 10px;
}
.userpage-content-record-time-box:before,
.userpage-content-record-time-box:after {
  content: "";
  position: absolute;
  height: 7px;
  width: 0px;
  background: #ffd800;
  transition: 300ms;
  -webkit-transition: 1s;
  -ms-transition: 1s;
  opacity: 0.3;
  left: 50%;
}
.userpage-content-record-time-box:before {
  bottom: 0;
}
.userpage-content-record-time-box:after {
  top: 0;
}
.userpage-content-record-time-box:hover {
  letter-spacing: 20px;
}
.userpage-content-record-time-box:hover:before,
.userpage-content-record-time-box:hover:after {
  width: 100%;
  opacity: 1;
  left: 0;
}
.userpage-content-record-time-box:hover {
  background: #171717;
  color: #fff;
}
.userpage-content-record-inform-box {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  top: 20%;
}
.userpage-content-record-inform {
  position: relative;
  width: 700px;
  height: 90px;
  top: 5%;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 36px;
  text-align: center;
  margin-bottom: 30px;
  padding-top: 20px;
  color: white;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border-radius: 25px;
  background: #3c3b3f;
  background: -webkit-linear-gradient(to right, #605c3c, #3c3b3f);
  background: linear-gradient(to right, #605c3c, #3c3b3f);
  box-shadow: 0 0 1px 2px rgba(200, 230, 255, 0.5),
    0 -1px 1px 3px rgba(200, 230, 255, 0.5) inset,
    0 0 1px 5px rgba(135, 131, 171, 0.9),
    0 0 1px 3.5px rgba(135, 131, 171, 0.9) inset,
    0 0 6px 6.5px rgba(123, 108, 196, 0.9),
    0 0 6px 5.5px rgba(123, 108, 196, 0.9) inset,
    0 4px 15px 9px rgba(0, 0, 0, 0.8), 0 4px 12.5px 6px rgba(0, 0, 0, 0.7) inset;
}
.userpage-content-record-inform:hover {
  background: #3c3b3f;
  background: -webkit-linear-gradient(to right, #605c3c, #3c3b3f);
  background: linear-gradient(to right, #605c3c, #3c3b3f);
  box-shadow: 0 0 2px 4px rgba(204, 204, 204, 0.5),
    0 -2px 2px 3px rgba(204, 204, 204, 0.5) inset,
    0 0 2px 10px rgba(120, 120, 120, 0.9),
    0 0 2px 7px rgba(120, 120, 120, 0.9) inset,
    0 0 12px 13px rgba(67, 67, 67, 0.9),
    0 0 12px 11px rgba(67, 67, 67, 0.9) inset,
    0 8px 30px 18px rgba(0, 0, 0, 0.8), 0 8px 25px 12px rgba(0, 0, 0, 0.7) inset;
}
/* .userpage-content-record-pagination {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  font-family: "Inter";
  font-size: 30px;
  color: black;
} */
.userpage-content-intro-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: bolder; */
  font-size: 32px;
  line-height: 44px;
  text-align: center;
  color: #ffffff;
}
</style>
