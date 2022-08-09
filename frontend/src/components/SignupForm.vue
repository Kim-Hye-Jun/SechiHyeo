<template>
  <div class="contents">
    <background></background>
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="signUp" class="form">
        <h2><b>회원 가입</b></h2>
        <div>
          <label for="userId">아이디: </label>
          <input id="userId" type="text" v-model="member.loginId" />
          <!-- <div v-show="!validId">아이디는 5 ~ 12 글자로 작성해주세요.</div> -->
        </div>
        <div>
          <label for="password">비밀번호: </label>
          <input id="password" type="text" v-model="member.loginPassword" />
        </div>
        <div>
          <label for="password">비밀번호 확인: </label>
          <input id="password" type="text" v-model="member.loginPassword2" />
        </div>
        <div>
          <label for="nickname">닉네임: </label>
          <input id="nickname" type="text" v-model="member.nickname" />
        </div>
        <div>
          <label for="email">이메일: </label>
          <input id="email" type="text" v-model="member.email" />
        </div>
        <div>
          <label for="phoneNumber">전화번호: </label>
          <input
            id="phoneNumber"
            type="text"
            v-model="member.phoneNumber"
            oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
          />
        </div>
        <button type="submit" class="btn" :disabled="isDisabled">
          회원 가입
        </button>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { registerUser } from "@/api/auth";
import Background from "./common/Background.vue";

export default defineComponent({
  components: { Background },
  data() {
    return {
      // form values
      member: {
        loginId: "",
        nickname: "",
        loginPassword: "",
        email: "",
        phoneNumber: "",
      },
      loginPassword2: "",
      IdMessage: "",
      passMessage: "",
      passCheckMessage: "",
      nickNameMessage: "",
      emailMesssage: "",
      phoneMessage: "",
    };
  },
  computed: {
    validId(): boolean {
      return this.member.loginId.length > 4 && this.member.loginId.length < 13;
    },
    validName(): boolean {
      return this.member.nickname.length > 0;
    },
    validPw(): boolean {
      return (
        this.member.loginPassword.length > 3 &&
        this.member.loginPassword.length < 13
      );
    },
    validPw_Re(): boolean {
      return (
        this.member.loginPassword == this.loginPassword2 &&
        this.member.loginPassword.length > 0
      );
    },
    validEmail(): boolean {
      const exptext = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
      return exptext.test(this.member.email);
    },
    // 회원가입 버튼 활성화
    isDisabled() {
      if (
        this.validId &&
        this.validName &&
        this.validPw &&
        this.validPw_Re &&
        this.validEmail
      ) {
        return false;
      } else {
        return true;
      }
    },
  },
  methods: {
    async signUp() {
      const response = await registerUser(this.member);
      console.log(response);
      this.initForm();
      alert(response.data);
    },
    initForm() {
      this.member.loginId = "";
      this.member.nickname = "";
      this.member.loginPassword = "";
      this.member.email = "";
      this.member.phoneNumber = "";
      this.loginPassword2 = "";
    },
  },
});
</script>
<style scoped></style>
