<template>
  <div class="userpage">
    <Background></Background>
    <UserPage_user></UserPage_user>
    <div>
      <div
        class="userpage-content-button"
        @click="recordClick"
        :class="[record === true ? 'click' : 'non-click']"
      >
        <span class="span-user">자기소개</span>
        <div class="transition"></div>
      </div>
      <!-- <div
        class="userpage-content-button"
        @click="introClick"
        :class="[intro === true ? 'click' : 'non-click']"
      >
        <span class="span-user">한줄소개</span>
        <div class="transition"></div>
      </div> -->
      <div
        class="userpage-content-button"
        @click="recruitClick"
        :class="[recruit === true ? 'click' : 'non-click']"
      >
        <span class="span-user">토론모집</span>
        <div class="transition"></div>
      </div>
      <div
        class="userpage-content-button"
        @click="applyClick"
        :class="[apply === true ? 'click' : 'non-click']"
      >
        <span class="span-user">토론신청</span>
        <div class="transition"></div>
      </div>
      <div
        class="userpage-content-button"
        @click="setClick"
        :class="[set === true ? 'click' : 'non-click']"
      >
        <span class="span-user">설 정</span>
        <div class="transition"></div>
      </div>
    </div>
    <div
      class="userpage-content-record"
      :class="[record === true ? '' : 'hidden']"
    >
      <UserPage_record></UserPage_record>
    </div>
    <div
      class="userpage-content-intro"
      :class="[intro === true ? '' : 'hidden']"
    >
      <UserPage_intro></UserPage_intro>
    </div>
    <div
      class="userpage-content-recruit"
      :class="[recruit === true ? '' : 'hidden']"
    >
      <UserPage_recruit></UserPage_recruit>
    </div>
    <div
      class="userpage-content-apply"
      :class="[apply === true ? '' : 'hidden']"
    >
      <UserPage_apply></UserPage_apply>
    </div>
    <div class="userpage-content-set" :class="[set === true ? '' : 'hidden']">
      <UserPage_set></UserPage_set>
    </div>
  </div>
</template>

<script lang="ts">
import Background from "@/components/common/Background.vue";
import UserPage_user from "@/components/organisms/user-Page/UserPage_user.vue";
import UserPage_record from "@/components/organisms/user-Page/UserPage_record.vue";
import UserPage_intro from "@/components/organisms/user-Page/UserPage_intro.vue";
import UserPage_recruit from "@/components/organisms/user-Page/UserPage_recruit.vue";
import UserPage_apply from "@/components/organisms/user-Page/UserPage_apply.vue";
import UserPage_set from "@/components/organisms/user-Page/UserPage_set.vue";
import { defineComponent } from "vue";
export default defineComponent({
  components: {
    Background,
    UserPage_user,
    UserPage_record,
    UserPage_intro,
    UserPage_recruit,
    UserPage_apply,
    UserPage_set,
  },
  data() {
    return {
      record: true,
      intro: false,
      recruit: false,
      apply: false,
      set: false,
    };
  },
  methods: {
    recordClick() {
      (this.record = true),
        (this.intro = false),
        (this.recruit = false),
        (this.apply = false),
        (this.set = false);
    },
    introClick() {
      (this.record = false),
        (this.intro = true),
        (this.recruit = false),
        (this.apply = false),
        (this.set = false);
    },
    recruitClick() {
      (this.record = false),
        (this.intro = false),
        (this.recruit = true),
        (this.apply = false),
        (this.set = false);
    },
    applyClick() {
      (this.record = false),
        (this.intro = false),
        (this.recruit = false),
        (this.apply = true),
        (this.set = false);
    },
    setClick() {
      (this.record = false),
        (this.intro = false),
        (this.recruit = false),
        (this.apply = false),
        (this.set = true);
    },
  },
});
</script>
<style scoped>
* {
  font-family: "locus_sangsang";
}
.hidden {
  display: none;
}
.click {
  background: #03e9f4;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 200px #03e9f4;
  -webkit-box-reflect: below 1px linear-gradient(transparent, #0005);
}
.click .span-user {
  color: #050801;
}
.non-click {
  border: #03e9f4 3px solid;
  border-radius: 10px;
}
.userpage {
  margin-left: 50%;
  transform: translate(-50%);
  width: 100vw;
  height: 100vh;
}
.userpage-content-button {
  position: relative;
  display: inline-block;
  font-family: "Raleway", sans-serif;
  font-weight: bold;
  left: 53.4%;
  transform: translate(-300%);
  margin: 0px 10px 5px 10px;
  width: 100px;
  top: 305px;
  font-size: 16px;
  line-height: 32px;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  outline: 0;
  padding: 6px 3px;
  color: #fff;
  overflow: hidden;
}
.userpage-content-button span {
  width: 100%;
  position: relative;
  -moz-transition: all 0.65s cubic-bezier(0.77, 0, 0.175, 1);
  -o-transition: all 0.65s cubic-bezier(0.77, 0, 0.175, 1);
  -webkit-transition: all 0.65s cubic-bezier(0.77, 0, 0.175, 1);
  transition: all 0.65s cubic-bezier(0.77, 0, 0.175, 1);
}
.transition {
  position: absolute;
  top: -10%;
  left: 0%;
  width: 100%;
  height: 0%;
  background: #80ffd3;
  z-index: -1;
  -ms-transform: skewX(-50deg);
  -webkit-transform: skewX(-50deg);
  transform: skewX(-50deg);
}
.userpage-content-button:hover span {
  color: #80ffd3 !important;
}
.userpage-content-button:hover .transition {
  height: 100%;
  top: 101%;
  -moz-transition: all 0.7s cubic-bezier(0.77, 0, 0.175, 1);
  -o-transition: all 0.7s cubic-bezier(0.77, 0, 0.175, 1);
  -webkit-transition: all 0.7s cubic-bezier(0.77, 0, 0.175, 1);
  transition: all 0.7s cubic-bezier(0.77, 0, 0.175, 1);
}
.userpage-content-record {
  position: relative;
  width: 600px;
  height: 370px;
  top: 310px;
  left: 50%;
  transform: translate(-50%);
  backdrop-filter: blur(4px);
  background: #757f9a;
  box-shadow: 0 0 0.5px 1px rgba(200, 230, 255, 0.5),
    0 -0.5px 0.5px 1.5px rgba(200, 230, 255, 0.5) inset,
    0 0 0.5px 2.5px rgba(135, 131, 171, 0.9),
    0 0 0.5px 1.75px rgba(135, 131, 171, 0.9) inset,
    0 0 3px 3.25px rgba(123, 108, 196, 0.9),
    0 0 3px 2.75px rgba(123, 108, 196, 0.9) inset,
    0 2px 7.5px 4.5px rgba(0, 0, 0, 0.8),
    0 2px 6.25px 3px rgba(0, 0, 0, 0.7) inset;
  border: 0px;
}
.userpage-content-intro {
  position: relative;
  top: 460px;
  left: 50%;
  transform: translate(-50%);
  width: 800px;
  height: 200px;
  background: #757f9a;
  box-shadow: 0 0 0.5px 1px rgba(200, 230, 255, 0.5),
    0 -0.5px 0.5px 1.5px rgba(200, 230, 255, 0.5) inset,
    0 0 0.5px 2.5px rgba(135, 131, 171, 0.9),
    0 0 0.5px 1.75px rgba(135, 131, 171, 0.9) inset,
    0 0 3px 3.25px rgba(123, 108, 196, 0.9),
    0 0 3px 2.75px rgba(123, 108, 196, 0.9) inset,
    0 2px 7.5px 4.5px rgba(0, 0, 0, 0.8),
    0 2px 6.25px 3px rgba(0, 0, 0, 0.7) inset;
  border: 0px;
}
.userpage-content-recruit {
  position: relative;
  width: 800px;
  height: 370px;
  top: 310px;
  left: 50%;
  transform: translate(-50%);
}
.userpage-content-apply {
  position: relative;
  width: 800px;
  height: 370px;
  top: 310px;
  left: 50%;
  transform: translate(-50%);
}
.userpage-content-set {
  position: relative;
  width: 600px;
  height: 370px;
  top: 310px;
  left: 50%;
  transform: translate(-50%);
  backdrop-filter: blur(4px);
  background: #757f9a;
  box-shadow: 0 0 0.5px 1px rgba(200, 230, 255, 0.5),
    0 -0.5px 0.5px 1.5px rgba(200, 230, 255, 0.5) inset,
    0 0 0.5px 2.5px rgba(135, 131, 171, 0.9),
    0 0 0.5px 1.75px rgba(135, 131, 171, 0.9) inset,
    0 0 3px 3.25px rgba(123, 108, 196, 0.9),
    0 0 3px 2.75px rgba(123, 108, 196, 0.9) inset,
    0 2px 7.5px 4.5px rgba(0, 0, 0, 0.8),
    0 2px 6.25px 3px rgba(0, 0, 0, 0.7) inset;
  border: 0px;
}
</style>
