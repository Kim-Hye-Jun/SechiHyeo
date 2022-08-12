<template>
  <div class="contents">
    <background></background>
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="signUp" class="form">
        <h2><b>회원 가입</b></h2>
        <div>
          <label for="userId">아이디: </label>
          <input id="userId" type="text" v-model="member.loginId" />
          <button type="button" @click="idDuplicateCheck">
            아이디 중복 체크
          </button>
          <div>{{ idMessage }}</div>
        </div>
        <div>
          <label for="password">비밀번호: </label>
          <input id="password" type="text" v-model="member.loginPassword" />
        </div>
        <div>
          <label for="password">비밀번호 확인: </label>
          <input id="password" type="text" v-model="loginPassword2" />
          <div>{{ pwMessage }}</div>
        </div>
        <div>
          <label for="nickname">닉네임: </label>
          <input id="nickname" type="text" v-model="member.nickname" />
          <button type="button" @click="nickNameDuplicateCheck">
            닉네임 중복 체크
          </button>
          <div>{{ nickNameMessage }}</div>
        </div>
        <div>
          <label for="email">이메일: </label>
          <input id="email" type="text" v-model="member.email" />
          <button type="button" @click="emailDuplicateCheck">
            이메일 중복 체크
          </button>
          <div>{{ emailMessage }}</div>
        </div>
        <div>
          <label for="phoneNumber">전화번호: </label>
          <input id="phoneNumber" type="text" v-model="member.phoneNumber" />
          <button type="button" @click="phoneDuplicateCheck">
            전화번호 중복 체크
          </button>
          <div>{{ phoneMessage }}</div>
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
import {
  registerUser,
  checkUserId,
  checkNickName,
  checkEmail,
  checkPhone,
} from "@/api/auth";
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
      idDuplicate: true,
      nickNameDuplicate: true,
      emailDuplicate: true,
      phoneDuplicate: true,
    };
  },
  computed: {
    validId(): boolean {
      return this.member.loginId.length > 4 && this.member.loginId.length < 13;
    },
    validName(): boolean {
      return this.member.nickname.length > 3;
    },
    validPw(): boolean {
      return (
        this.member.loginPassword.length > 3 &&
        this.member.loginPassword.length < 13
      );
    },
    validPw_Re(): boolean {
      return this.member.loginPassword === this.loginPassword2;
    },
    validEmail(): boolean {
      const exptext = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
      return exptext.test(this.member.email);
    },
    validPhone(): boolean {
      // oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
      const exptext = /^[0-9]+$/;
      return exptext.test(this.member.phoneNumber);
    },
    // 회원가입 버튼 활성화
    isDisabled() {
      if (
        this.validId &&
        this.validName &&
        this.validPw &&
        this.validPw_Re &&
        this.validEmail &&
        this.validPhone &&
        !this.idDuplicate &&
        !this.nickNameDuplicate &&
        !this.emailDuplicate &&
        !this.phoneDuplicate
      ) {
        return false;
      } else {
        return true;
      }
    },
    idMessage() {
      if (!this.validId) {
        return "아이디의 길이는 5~12자입니다.";
      } else if (this.idDuplicate) {
        return "아이디 중복 체크 해주세요.";
      } else {
        return "사용가능한 아이디 입니다.";
      }
    },
    pwMessage() {
      if (!this.validPw) {
        return "비밀번호 길이는 4~12자입니다.";
      } else if (this.validPw_Re) {
        return "비밀번호가 일치합니다.";
      } else {
        return "비밀번호가 일치하지 않습니다.";
      }
    },
    nickNameMessage() {
      if (!this.validName) {
        return "닉네임의 길이를 3글자 이상입니다.";
      } else if (this.nickNameDuplicate) {
        return "닉네임 중복 체크 해주세요.";
      } else {
        return "사용가능한 닉네임 입니다.";
      }
    },
    emailMessage() {
      if (!this.validEmail) {
        return "이메일 양식을 지켜주세요";
      } else if (this.emailDuplicate) {
        return "이메일 중복 체크 해주세요.";
      } else {
        return "사용가능한 이메일 입니다.";
      }
    },
    phoneMessage() {
      if (!this.validPhone) {
        return "전화번호 양식을 지켜주세요";
      } else if (this.phoneDuplicate) {
        return "전화 중복 체크 해주세요.";
      } else {
        return "사용가능한 전화번호 입니다.";
      }
    },
  },
  methods: {
    async idDuplicateCheck() {
      try {
        const response = await checkUserId(this.member.loginId);
        this.idDuplicate = false;
      } catch {
        alert("이미 사용중인 아이디 입니다!");
      }
    },
    async nickNameDuplicateCheck() {
      try {
        const response = await checkNickName(this.member.loginId);
        this.nickNameDuplicate = false;
      } catch {
        alert("이미 사용중인 닉네임 입니다!");
      }
    },
    async emailDuplicateCheck() {
      try {
        const response = await checkEmail(this.member.email);
        // console.log(response);
        this.emailDuplicate = false;
      } catch {
        alert("이미 사용중인 이메일 입니다!");
      }
    },
    async phoneDuplicateCheck() {
      try {
        const response = await checkPhone(this.member.phoneNumber);
        // console.log(response);
        this.phoneDuplicate = false;
      } catch {
        alert("이미 사용중인 핸드폰 번호 입니다!");
      }
    },
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
