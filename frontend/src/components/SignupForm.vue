<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.stop.prevent class="form">
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
        <button
          type="button"
          class="btn"
          @click="signup"
          :disabled="isDisabled"
        >
          회원 가입
        </button>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore, mapState } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    // console.log(store);

    return { store };
  },
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
    };
  },
  computed: {
    // ...mapState(["checkedId", "checkedEmail", "compare_id", "compare_email"]),
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
        // this.member.loginPassword == this.loginPassword2 &&
        // this.member.loginPassword.length > 0
        true
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
    // idDuplicateCheck() { // 아이디 중복확인용
    //   this.$store.dispatch("DuplicateId", this.user.userid);
    // },
    // emailDuplicateCheck() { // 이메일 중복 확인용
    //   this.$store.dispatch("DuplicateEmail", this.user.email);
    // },
    signup() {
      // if (!this.checkedId || this.member.userid != this.compare_id) {
      //   alert("아이디 중복체크를 해주세요");
      // } else if (!this.checkedEmail || this.member.email != this.compare_email) {
      //   alert("이메일 중복체크를 해주세요");
      // } else {
      this.store.dispatch("memberSignup", this.member);
      // }
    },
  },
});
</script>
<style scoped></style>
