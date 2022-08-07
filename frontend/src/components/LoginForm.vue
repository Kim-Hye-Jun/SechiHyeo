<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="login" class="form">
        <div>
          <label for="userID">아이디:</label>
          <input
            id="userID"
            type="text"
            v-model="member.loginId"
            placeholder="아이디를 입력하세요."
          />
        </div>
        <div>
          <label for="userPW">패스워드:</label>
          <input
            id="userPW"
            type="text"
            v-model="member.loginPassword"
            placeholder="비밀번호를 입력하세요."
          />
        </div>
        <button type="submit" class="btn">로그인</button>
      </form>
      <div class="container text-center">
        <!-- <router-link :to="{ name: 'userid' }" class="find"
          >아이디 찾기</router-link
        >
        /
        <router-link :to="{ name: 'usercheck' }" class="find"
          >비밀번호 재설정</router-link
        >
        <br /> -->
        <h6 class="text-center find mt-5">아직 회원이 아니신가요?</h6>
        <button
          class="w-100 btn btn-lg btn-outline-secondary signup-btn"
          type="button"
          @click="moveToSignin"
        >
          회원가입
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions } from "vuex";

export default defineComponent({
  data() {
    return {
      member: {
        loginId: "",
        loginPassword: "",
      },
    };
  },
  computed: {},
  methods: {
    ...mapActions(["LOGIN"]),
    async login() {
      if (this.member.loginId.length == 0) {
        const myDom = document.querySelector("#userID") as HTMLParagraphElement;
        alert("아이디를 입력해주세요.");
        myDom.focus();
      } else if (this.member.loginPassword.length == 0) {
        alert("비밀번호를 입력해주세요.");
        const myDom = document.querySelector("#userPW") as HTMLParagraphElement;
        myDom.focus();
      } else {
        try {
          await this.LOGIN(this.member);
          this.$router.push("/main");
        } catch (error) {
          console.log(error);
        } finally {
          this.initForm();
        }
        await this.LOGIN(this.member);

        // const myDom = document.querySelector("#userID") as HTMLParagraphElement;
        // myDom.focus();
      }
    },
    moveToSignin() {
      this.initForm();
      this.$router.push("signup");
    },
    initForm() {
      this.member.loginId = "";
      this.member.loginPassword = "";
    },
  },
});
</script>
<style scoped>
.form-login {
  width: 50%;
  margin: auto;
}
.btn {
  color: black;
  text-decoration: none;
  font-style: italic;
  font-weight: bold;
}
.find {
  color: black;
  text-decoration: none;
  font-size: 12px;
}
.signup-btn {
  color: black;
  text-decoration: none;
}
a {
  color: black;
  text-decoration: none;
}
</style>
