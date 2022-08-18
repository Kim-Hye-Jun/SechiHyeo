<template>
  <background></background>
  <!-- <div class="contents">
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
  </div> -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-12 col-lg-12">
        <div class="card signUp-content shadow-lg border-0">
          <div class="card-body">
            <div class="text-center">
              <img class="logo" src="@/assets/logo.png" alt="세치혀 로고" />
            </div>
            <h3 class="text-logo">회원 가입</h3>
            <br />
            <form class="text-center" @submit.prevent>
              <div>
                <label for="userId">아이디: </label>
                <div class="input-group mb-3">
                  <input
                    id="userId"
                    class="form-control border-0"
                    type="text"
                    v-model="member.loginId"
                    placeholder="아이디를 입력하세요."
                  />
                  <button
                    type="button"
                    class="btn btn-outline-primary"
                    @click="idDuplicateCheck"
                  >
                    중복 체크
                  </button>
                </div>
                <div
                  :class="{
                    inValid: !validId || this.idDuplicate,
                    Valid: validId && !this.idDuplicate,
                  }"
                >
                  {{ idMessage }}
                </div>
              </div>
              <br />
              <div>
                <label for="password">비밀번호: </label>
                <div class="input-group mb-3">
                  <input
                    id="password"
                    type="password"
                    class="form-control border-0"
                    v-model="member.loginPassword"
                    placeholder="비밀번호를 입력하세요."
                  />
                </div>
                <div
                  :class="{
                    inValid: !validPw,
                    Valid: validPw,
                  }"
                >
                  {{ pwMessage }}
                </div>
              </div>
              <br />
              <div>
                <label for="password">비밀번호 확인: </label>
                <div class="input-group mb-3">
                  <input
                    id="password"
                    type="password"
                    class="form-control border-0"
                    v-model="loginPassword2"
                    placeholder="비밀번호를 확인해주세요."
                  />
                </div>
                <div
                  :class="{
                    inValid: !validPw || !validPw_Re,
                    Valid: validPw && validPw_Re,
                  }"
                >
                  {{ pwReMessage }}
                </div>
              </div>
              <br />
              <div>
                <label for="nickname">닉네임: </label>
                <div class="input-group mb-3">
                  <input
                    id="nickname"
                    type="text"
                    class="form-control border-0"
                    :value="member.nickname"
                    @input="changeNickName"
                    placeholder="닉네임을 입력하세요"
                  />
                  <button
                    type="button"
                    class="btn btn-outline-primary"
                    @click="nickNameDuplicateCheck"
                  >
                    닉네임 중복 체크
                  </button>
                </div>
                <div
                  :class="{
                    inValid: !validName || this.nickNameDuplicate,
                    Valid: validName && !this.nickNameDuplicate,
                  }"
                >
                  {{ nickNameMessage }}
                </div>
              </div>
              <br />
              <div>
                <label for="email">이메일: </label>
                <div class="input-group mb-3">
                  <input
                    id="email"
                    type="text"
                    class="form-control border-0"
                    v-model="member.email"
                    placeholder="someone@example.com"
                  />
                  <button
                    type="button"
                    class="btn btn-outline-primary"
                    @click="emailDuplicateCheck"
                  >
                    이메일 중복 체크
                  </button>
                </div>
                <div
                  :class="{
                    inValid: !validEmail || this.emailDuplicate,
                    Valid: validEmail && !this.emailDuplicate,
                  }"
                >
                  {{ emailMessage }}
                </div>
              </div>
              <br />
              <div>
                <label for="phoneNumber">전화번호: </label>
                <div class="input-group mb-3">
                  <input
                    id="phoneNumber"
                    type="text"
                    class="form-control border-0"
                    v-model="member.phoneNumber"
                    placeholder="전화번호를 입력해주세요"
                  />
                  <button
                    type="button"
                    class="btn btn-outline-primary"
                    @click="phoneDuplicateCheck"
                  >
                    전화번호 중복 체크
                  </button>
                </div>
                <div
                  :class="{
                    inValid: !validPhone || this.phoneDuplicate,
                    Valid: validPhone && !this.phoneDuplicate,
                  }"
                >
                  {{ phoneMessage }}
                </div>
              </div>
            </form>
          </div>
          <div class="nomember">
            <p class="text-center">
              <button
                type="submit"
                class="btn btn-success btn-lg"
                :disabled="isDisabled"
                @click="signUp"
              >
                회원 가입
              </button>
            </p>
          </div>
        </div>
      </div>
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
      return this.member.nickname.length > 2;
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
      return (
        exptext.test(this.member.phoneNumber) &&
        this.member.phoneNumber.length > 5
      );
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
      } else {
        return "가능한 비밀번호 입니다.";
      }
    },
    pwReMessage() {
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
        return "숫자만 입력해주세요";
      } else if (this.phoneDuplicate) {
        return "전화 중복 체크 해주세요.";
      } else {
        return "사용가능한 전화번호 입니다.";
      }
    },
  },
  methods: {
    changeNickName(e: any) {
      //한글 v-model 버그 해결을 위한 함수
      this.member.nickname = e.target.value;
    },
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
        const response = await checkNickName(this.member.nickname);
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
      this.$router.push("/login");
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
<style scoped>
body {
  background-color: #0278ae;
  font-family: "Lato", sans-serif;
}

.container {
  margin-top: 70px;
  margin-bottom: 25%;
}

.signUp-content {
  max-width: 600px;
  width: 100%;
  height: 1100px;
  z-index: 1;
  position: relative;
  top: 38%;
  left: 47%;
  margin-left: -200px;
  margin-top: -286px;
  border-radius: 8px;
  /* padding-bottom: 70px; */
}

.logo {
  width: 128px;
  height: 128px;
  margin: 5px;
}

.text-logo {
  text-align: center;
  font-weight: bold;
  font-size: 32px;
}

.form-control {
  width: 12rem;
  height: 3rem;
  /* left: 65px; */

  position: relative;
  border-radius: 5px;
  background-color: rgb(232, 240, 254);
}

/* .btn {
  font-size: 22px;
  background-color: #0278ae;
  border: none;
  width: 18rem;
  height: 3rem;
  border-radius: 5px;
}

.btn:hover {
  background-color: blue;
} */

.nomember {
  background-color: #e4dede;
  padding: 10px;
  padding-top: 20px;
  border-radius: 0px 0px 5px 5px;
}

.nomember a {
  text-decoration: none;
}

.copyright {
  color: white;
  padding: 15px;
}

/*support google chrome*/
.form-control::-webkit-input-placeholder {
  color: #00000036;
}

/*support mozilla*/
.form-control:-moz-input-placeholder {
  color: red;
}

/*support internet explorer*/
.form-control:-ms-input-placeholder {
  color: red;
}

.inValid {
  color: red;
}

.Valid {
  color: green;
}
</style>
