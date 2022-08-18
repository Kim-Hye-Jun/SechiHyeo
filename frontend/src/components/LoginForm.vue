<template>
  <!-- <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent class="form">
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
        <button type="button" class="btn" @click="login">로그인</button>
      </form>
      <div class="container text-center">
        <router-link :to="{ name: 'userid' }" class="find"
          >아이디 찾기</router-link
        >
        /
        <router-link :to="{ name: 'usercheck' }" class="find"
          >비밀번호 재설정</router-link
        >
        <br />
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
  </div> -->
  <background></background>
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-12 col-lg-12">
        <div class="card login-content shadow-lg border-0">
          <div class="card-body">
            <div class="text-center">
              <!-- <img
                class="logo"
                src="https://cdn3.iconfinder.com/data/icons/galaxy-open-line-gradient-i/200/account-256.png"
              /> -->
              <img class="logo" src="@/assets/logo.png" alt="세치혀 로고" />
            </div>
            <h3 class="text-logo">Sign In</h3>
            <br />
            <form class="text-center" @submit.prevent>
              <input
                id="userID"
                class="form-control border-0"
                type="text"
                v-model="member.loginId"
                placeholder="아이디를 입력하세요."
              />
              <br />
              <input
                class="form-control border-0"
                id="userPW"
                type="password"
                v-model="member.loginPassword"
                placeholder="비밀번호를 입력하세요."
              />
              <br />
              <button
                class="btn btn-outline-primary btn-lg"
                type="button"
                @click="login"
              >
                로그인
              </button>
            </form>
          </div>
          <div class="nomember">
            <p class="text-center">회원이 아니신가요?</p>
            <div class="text-center">
              <button
                class="btn btn-outline-primary btn-lg signup-btn"
                type="button"
                @click="moveToSignin"
              >
                회원가입
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapActions } from "vuex";
import Background from "./common/Background.vue";

export default defineComponent({
  components: { Background },
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
    ...mapActions(["loginMember"]),
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
          await this.loginMember(this.member);
          this.$router.push("/main");
        } catch (error) {
          alert("로그인 정보를 확인해주세요!");
        } finally {
          this.initForm();
        }
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
/* .form-login {
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
} */
body {
  background-color: #0278ae;
  font-family: "Lato", sans-serif;
}

.login-content {
  max-width: 450px;
  width: 100%;
  height: 550px;
  z-index: 1;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-left: -200px;
  margin-top: -286px;
  border-radius: 8px;
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
  width: 18rem;
  height: 3rem;
  left: 65px;
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
</style>
