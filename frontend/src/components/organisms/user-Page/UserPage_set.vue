<template>
  <div class="userpage-content-set-form-topic">회원정보 수정</div>
  <div style="position: absolute">
    <div style="margin-left: 80px; height: 150px"></div>
    <image-upload></image-upload>
  </div>
  <!-- <div class="userpage-content-set-form-image"></div> -->
  <!-- <button class="userpage-content-set-form-fix">사진 편집</button> -->
  <div class="userpage-content-set-form-personal">
    <div class="userpage-content-set-form-id">ID</div>
    <div class="userpage-content-set-form-pw">PW</div>
    <div class="userpage-content-set-form-pw2">PW2</div>
    <div class="userpage-content-set-form-pw3">PW3</div>
    <div class="userpage-content-set-form-email">E-mail</div>
    <div class="userpage-content-set-form-phone">Phone</div>
    <input
      class="userpage-content-set-form-input-id"
      v-model="memberinfo.nickname"
    />
    <input
      class="userpage-content-set-form-input-pw"
      type="password"
      placeholder="현재 비밀번호"
      v-model="ps"
    />
    <input
      class="userpage-content-set-form-input-pw2"
      type="password"
      placeholder="새 비밀번호"
      v-model="newps1"
    />
    <input
      class="userpage-content-set-form-input-pw3"
      type="password"
      placeholder="새 비밀번호 확인"
      v-model="newps2"
    />
    <input
      class="userpage-content-set-form-input-email"
      v-model="memberinfo.email"
    />
    <input
      class="userpage-content-set-form-input-phone"
      v-model="memberinfo.phoneNumber"
    />
  </div>
  <input
    class="userpage-content-set-form-intro"
    type="text"
    placeholder="한줄 소개"
    v-model="memberinfo.introduce"
  />
  <!-- <div class="userpage-content-set-form-intro-word">글자 수</div> -->
  <button class="userpage-content-set-form-button" @click="profileUpdate">
    수정 완료
  </button>
  <button class="userpage-content-set-form-button2" @click="profileDelete">
    회원 탈퇴
  </button>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions, mapState } from "vuex";
import ImageUpload from "../../molecules/ImageUpload.vue";
export default defineComponent({
  components: { ImageUpload },
  data() {
    return {
      ps: "",
      newps1: "",
      newps2: "",
    };
  },
  computed: {
    ...mapState(["memberinfo"]),
  },
  // putProfileImage,
  methods: {
    ...mapActions(["PROFILEUPDATE", "PASSWORD", "AUTHPW", "PROFILEDELETE"]),
    async profileUpdate() {
      if (await this.AUTHPW()) {
        this.PASSWORD();
        this.PROFILEUPDATE();
      } else {
        alert("비밀번호가 틀렸습니다!!");
      }
    },
    profileDelete() {
      if (this.memberinfo.loginPassword === this.ps) {
        this.PROFILEDELETE(this.memberinfo);
      } else {
        alert("비밀번호가 일치하지 않습니다!!!");
      }
    },
  },
});
</script>

<style scoped>
* {
  font-family: "locus_sangsang";
}
.userpage-content-set-form-intro {
  position: absolute;
  width: 400px;
  height: 115px;
  left: 20px;
  top: 245px;
  background: #acacac;
  border: #061161 solid 1px;
  border-radius: 15px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-intro:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-intro-word {
  position: absolute;
  width: 120px;
  height: 40px;
  bottom: 30px;
  right: 80px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: 400; */
  font-size: 20px;
  line-height: 24px;
  color: #ffffff;
}
.userpage-content-set-form-button {
  position: absolute;
  width: 100px;
  height: 40px;
  transform: translate(-50%);
  top: 260px;
  left: 500px;
  background: #070707;
  border: #ffffff solid 1px;
  border-radius: 5px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: bold; */
  font-size: 16px;
  line-height: 36px;
  color: #ffffff;
}
.userpage-content-set-form-button:hover {
  background: #ff0080;
  box-shadow: 0 0 1px #ff0080, 0 0 5px #ff0080, 0 0 10px #ff0080,
    0 0 20px #ff0080;
}
.userpage-content-set-form-button2 {
  position: absolute;
  width: 100px;
  height: 40px;
  transform: translate(-50%);
  top: 310px;
  left: 500px;
  background: #070707;
  border: #ffffff solid 1px;
  border-radius: 5px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: bold; */
  font-size: 16px;
  line-height: 36px;
  color: #ffffff;
}
.userpage-content-set-form-button2:hover {
  background: #ff0080;
  box-shadow: 0 0 1px #ff0080, 0 0 5px #ff0080, 0 0 10px #ff0080,
    0 0 20px #ff0080;
}
.userpage-content-set-form-button-text:hover {
  font-weight: bold;
  color: #070707;
}
.userpage-content-set-form-topic {
  position: relative;
  left: 34%;
  top: 10px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: bold; */
  font-size: 30px;
  color: #ffffff;
}
/* .userpage-content-set-form-topic:hover {
  text-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 20px #ff0080, 0 0 30px #ff0080,
    0 0 40px #ff0080, 0 0 55px #ff0080, 0 0 75px #ff0080;
} */
.userpage-content-set-form-personal {
  position: relative;
  left: 300px;
  /* margin-left: 340px; */
}
.userpage-content-set-form-input-phone {
  box-sizing: border-box;
  position: absolute;
  width: 240px;
  height: 28px;
  left: 40px;
  top: 165px;
  background: #acacac;
  border: 1px solid #000000;
  border-radius: 5px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-input-phone:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-input-email {
  box-sizing: border-box;
  position: absolute;
  width: 240px;
  height: 28px;
  left: 40px;
  top: 135px;
  background: #acacac;
  border: 1px solid #000000;
  border-radius: 5px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-input-email:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-input-pw {
  box-sizing: border-box;
  position: absolute;
  width: 240px;
  height: 28px;
  left: 40px;
  top: 45px;
  background: #acacac;
  border: 1px solid #000000;
  border-radius: 5px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-input-pw:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-input-pw2 {
  box-sizing: border-box;
  position: absolute;
  width: 240px;
  height: 28px;
  left: 40px;
  top: 75px;
  background: #acacac;
  border: 1px solid #000000;
  border-radius: 5px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-input-pw2:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-input-pw3 {
  box-sizing: border-box;
  position: absolute;
  width: 240px;
  height: 28px;
  left: 40px;
  top: 105px;
  background: #acacac;
  border: 1px solid #000000;
  border-radius: 5px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-input-pw3:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-input-id {
  box-sizing: border-box;
  position: absolute;
  width: 240px;
  height: 28px;
  left: 40px;
  top: 15px;
  background: #acacac;
  border: 1px solid #000000;
  border-radius: 5px;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  font-weight: bold;
}
.userpage-content-set-form-input-id:focus {
  color: white;
  background: #000000;
  border: white solid 1px;
}
.userpage-content-set-form-phone {
  position: absolute;
  width: 270px;
  height: 24px;
  left: -50px;
  top: 160px;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 20px;
  color: #000000;
}
.userpage-content-set-form-email {
  position: absolute;
  width: 270px;
  height: 24px;
  left: -50px;
  top: 130px;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 20px;
  color: #000000;
}
.userpage-content-set-form-pw {
  position: absolute;
  width: 300px;
  height: 24px;
  left: -50px;
  top: 40px;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 20px;
  color: #000000;
}
.userpage-content-set-form-pw2 {
  position: absolute;
  width: 300px;
  height: 24px;
  left: -50px;
  top: 70px;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 20px;
  color: #000000;
}
.userpage-content-set-form-pw3 {
  position: absolute;
  width: 300px;
  height: 24px;
  left: -50px;
  top: 100px;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 20px;
  color: #000000;
}
.userpage-content-set-form-id {
  position: absolute;
  width: 310px;
  height: 24px;
  left: -50px;
  top: 10px;
  /* font-family: "Inter"; */
  font-style: normal;
  font-size: 20px;
  color: #000000;
}
</style>
