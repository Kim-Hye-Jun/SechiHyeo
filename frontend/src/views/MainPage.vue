<template>
  <div id="contents">
    <div id="section1">
      <div class="container">
        <div class="content__box">
          <img class="content__box__img" src="@/assets/logo.png" />
          <div class="content__box__discription">^^</div>
        </div>
        <a href="#section2" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section2">
      <div class="container">
        <div class="content__box">
          <img class="content__box__img" src="@/assets/logo.png" />
          <div class="content__box__discription">^^</div>
        </div>
        <a href="#section3" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section3">
      <div class="container">
        <div class="content__box">
          <img class="content__box__img" src="@/assets/logo.png" />
          <div class="content__box__discription">^^</div>
        </div>
        <a href="#section4" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section4">
      <div class="container">
        <div class="content__box">
          <img class="content__box__img" src="@/assets/logo.png" />
          <div class="content__box__discription">^^</div>
        </div>
        <a href="#section5" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section5">
      <div class="container">
        <div class="content__box">
          <img class="content__box__img" src="@/assets/logo.png" />
          <div class="content__box__discription">^^</div>
        </div>
        <a href="#section6" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section6">
      <div class="container">
        <div class="content__box">
          <img class="content__box__img" src="@/assets/logo.png" />
          <div class="content__box__discription">^^</div>
        </div>
        <a href="#section7" class="scroll">scroll<span></span></a>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
// import { mapState } from "vuex";
import { OPENVIDU_SERVER_URL, OPENVIDU_SERVER_SECRET } from "@/config/index";

export default defineComponent({
  setup() {
    const store = useStore();
    return { store };
  },
});
</script>
<style scoped>
/* reset */
* {
  margin: 0;
  padding: 0;
  font-family: "Lato";
}
li {
  list-style: none;
}
a {
  text-decoration: none;
  font-family: "Abel";
}
/* contents */
#contents {
  text-align: center;
  color: #fff;
}
#contents > div {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}
#contents > div h2 {
  font-size: 70px;
  font-weight: 200;
  text-transform: uppercase;
}
#contents > div h2 strong {
  font-weight: 700;
  font-style: italic;
  display: block;
}

#section1 {
  background: #000000;
}

#section2 {
  background: black;
}

/* section */
#section3 {
  background: radial-gradient(
    circle,
    rgba(247, 150, 192, 1) 0%,
    rgba(118, 174, 241, 1) 100%
  );
}

#section4 {
  background: linear-gradient(315deg, #4dccc6 0%, #96e4df 74%);
}

#section5 {
  background: linear-gradient(315deg, #f0ecfc 0%, #c797eb 74%);
}

.container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: 1200px;
  margin: 0 auto;
}

.content__box__img {
  width: 40%;
}

.content__box__discription {
  width: 60%;
  padding: 3%;
  margin: 3%;
  border: 2px solid;
  border-radius: 9px;
}

/* scroll */
.scroll {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translatex(-50%);
  z-index: 2;
  display: inline-block;
  color: #fff;
  transition: opacity 0.3s;
}
.scroll:hover {
  opacity: 0.5;
}

a {
  padding-top: 60px;
}
a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 46px;
  height: 46px;
  margin-left: -23px;
  border: 1px solid #fff;
  border-radius: 100%;
  box-sizing: border-box;
}
a span::after {
  position: absolute;
  top: 50%;
  left: 50%;
  content: "";
  width: 16px;
  height: 16px;
  margin: -12px 0 0 -8px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  box-sizing: border-box;
}
a span::before {
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  content: "";
  width: 44px;
  height: 44px;
  box-shadow: 0 0 0 0 rgba(255, 255, 255, 0.1);
  border-radius: 100%;
  opacity: 0;
  animation: sdb03 3s infinite;
  box-sizing: border-box;
}
@keyframes sdb03 {
  0% {
    opacity: 0;
  }
  30% {
    opacity: 1;
  }
  60% {
    box-shadow: 0 0 0 60px rgba(255, 255, 255, 0.1);
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}

.content__box {
  width: 80%;
  height: 500px;
  background: #519dff;
  border-radius: 12px;
  /* opacity: 0.5; */
  /* box-shadow: 0 0 5px 2px rgba(231, 218, 218, 0.738); */

  display: flex;
  flex-direction: row;
}
</style>
