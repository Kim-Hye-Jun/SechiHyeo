<template>
  <div id="contents">
    <div id="section1">
      <div class="container">
        <h2>영상 토론 플랫폼</h2>
        <div style="font-size: 55px">세 치 혀</div>
        <a href="#section2" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section2">
      <div class="container">
        <h2>당신의 토론 능력을 키워보세요!</h2>
        <a href="#section3" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section3">
      <div class="container">
        <h2>토론을 처음 시작하는 사람부터</h2>
        <h2>대회를 준비하는 사람까지</h2>
        <a href="#section4" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section4">
      <div class="container">
        <h2>원하는 주제로 공식 토론 규칙에 맞춰</h2>
        <h2>토론을 진행해보세요</h2>
        <a href="#section5" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section5">
      <div class="container">
        <h2>토론의 A to Z</h2>
        <a href="#section6" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section6">
      <div class="container">
        <h2>지금 바로 토론해 보세요</h2>
        <a href="#section1" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <!-- <div id="section7">
      <div class="container">
        <h2>Scroll Down Button #7</h2>
        <a href="#section8" class="scroll"
          >scroll<span></span><span></span><span></span
        ></a>
      </div>
    </div>
    <div id="section8">
      <div class="container">
        <h2>Scroll Down Button #8</h2>
        <a href="#section9" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section9">
      <div class="container">
        <h2>Scroll Down Button #9</h2>
        <a href="#section10" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section10">
      <div class="container">
        <h2>Scroll Down Button #10</h2>
        <a href="#section11" class="scroll">scroll<span></span></a>
      </div>
    </div>
    <div id="section11">
      <div class="container">
        <h2>Scroll Down Button #11</h2>
        <a href="#section1" class="scroll">scroll<span></span></a>
      </div>
    </div> -->
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

    // document.addEventListener("DOMContentLoaded", function () {
    //   const a = document.getElementsByClassName("a[href*=#]");
    //   for (let i = 0; i < a.length; i++) {
    //     a[i].addEventListener("click", function (e) {
    //       e.preventDefault();
    //       document
    //         .getElementsByTagName("html")[0]
    //         .animate(
    //           { scrollTop: $($(this).attr("href")).offset().top },
    //           500,
    //           "linear"
    //         );
    //     });
    //   }
    // });

    // (function () {
    //   "use strict";

    //   var btnScrollDown = document.querySelector("a[href*=#]");

    //   function scrollDown() {
    //     var windowCoords = document.documentElement.clientHeight;
    //     (function scroll() {
    //       if (window.pageYOffset < windowCoords) {
    //         window.scrollBy(0, 10);
    //         setTimeout(scroll, 0);
    //       }
    //       if (window.pageYOffset > windowCoords) {
    //         window.scrollTo(0, windowCoords);
    //       }
    //     })();
    //   }
    //   if (btnScrollDown) {
    //     btnScrollDown.addEventListener("click", scrollDown);
    //   }
    // })();

    return { store };
  },
  // mounted() {
  //   $(function () {
  //     $("a[href*=#]").on("click", function (e: any) {
  //       e.preventDefault();
  //       $("html, body").animate(
  //         { scrollTop: $($(this).attr("href")).offset().top },
  //         500,
  //         "linear"
  //       );
  //     });
  //   });
  // },
  data() {
    return {
      mySessionId: "SessionA",
      nickName: "",
    };
  },
  computed: {},
  methods: {},
});
</script>

<style scoped>
/* reset */
* {
  margin: 0;
  padding: 0;
  font-family: "locus_sangsang";
}
li {
  list-style: none;
}
a {
  text-decoration: none;
  font-family: "Abel";
}

/* nav */
#nav {
  position: fixed;
  left: 0;
  top: 0px;
  width: 100%;
  height: 61px;
  z-index: 1000;
  background-color: rgba(255, 255, 255, 0.3);
  transition: all 0.3s ease;
}
#nav.on {
  position: fixed;
  top: -61px;
}
#nav h1 {
  float: left;
  color: #fff;
  font-size: 40px;
  padding: 5px 5px 5px 15px;
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

/* section */
#section1 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #a3a1ff,
    #3a3897
  );
}
#section2 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #ff9fcd,
    #3a3897
  );
}
#section3 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #e64efa,
    #3a3897
  );
}
#section4 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #00ae8e,
    #3a3897
  );
}
#section5 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #29abe2,
    #3a3897
  );
}
#section6 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #a3a1ff,
    #3a3897
  );
}
/* #section7 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #a3a1ff,
    #3a3897
  );
}
#section8 {
  background: radial-gradient(
    ellipse farthest-corner at center bottom,
    #3aa17e,
    #00537e
  );
}
#section9 {
  background: radial-gradient(
    ellipse farthest-corner at center top,
    #fbb03b,
    #d4145a
  );
}
#section10 {
  background-image: linear-gradient(-45deg, #ffc796 0%, #ff6b95 100%);
}
#section11 {
  background-image: linear-gradient(to top, #88d3ce 0%, #6e45e2 100%);
} */

/* #section1 {
  background: url(https://picsum.photos/1200/800?image=575) center center /
    cover no-repeat;
}
#section2 {
  background: url(https://picsum.photos/1200/800?image=1016) center center /
    cover no-repeat;
}
#section3 {
  background: url(https://picsum.photos/1200/800?image=869) center center /
    cover no-repeat;
}
#section4 {
  background: url(https://picsum.photos/1200/800?image=506) center center /
    cover no-repeat;
}
#section5 {
  background: url(https://picsum.photos/1200/800?image=1037) center center /
    cover no-repeat;
}
#section6 {
  background: url(https://picsum.photos/1200/800?image=901) center center /
    cover no-repeat;
}
#section7 {
  background: url(https://picsum.photos/1200/800?image=675) center center /
    cover no-repeat;
}
#section8 {
  background: url(https://picsum.photos/1200/800?image=1050) center center /
    cover no-repeat;
}
#section9 {
  background: url(https://picsum.photos/1200/800?image=902) center center /
    cover no-repeat;
}
#section10 {
  background: url(https://picsum.photos/1200/800?image=516) center center /
    cover no-repeat;
} */
/* container */
.container {
  width: 1200px;
  margin: 0 auto;
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

/* #section1 a {
  padding-top: 60px;
}
#section1 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  transform: rotate(-45deg);
  box-sizing: border-box;
}

#section2 a {
  padding-top: 60px;
}
#section2 a span {
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
#section2 a span::after {
  position: absolute;
  top: 50%;
  left: 50%;
  content: "";
  width: 16px;
  height: 16px;
  margin: -12px 0 0 -8px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  transform: rotate(-45deg);
  box-sizing: border-box;
} */

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

/* #section4 a {
  padding-top: 60px;
}
#section4 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  transform: rotate(-45deg);
  animation: sdb04 2s infinite;
  box-sizing: border-box;
}
@keyframes sdb04 {
  0% {
    transform: rotate(-45deg) translate(0, 0);
  }
  20% {
    transform: rotate(-45deg) translate(-10px, 10px);
  }
  40% {
    transform: rotate(-45deg) translate(0, 0);
  }
}

#section5 a {
  padding-top: 70px;
}
#section5 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  transform: rotate(-45deg);
  animation: sdb05 1.5s infinite;
  box-sizing: border-box;
}

@keyframes sdb05 {
  0% {
    transform: rotate(-45deg) translate(0, 0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    transform: rotate(-45deg) translate(-20px, 20px);
    opacity: 0;
  }
}

#section6 a {
  padding-top: 70px;
}
#section6 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  transform: rotateZ(-45deg);
  animation: sdb06 1.5s infinite;
  box-sizing: border-box;
}
@keyframes sdb06 {
  0% {
    transform: rotateY(0) rotateZ(-45deg) translate(0, 0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    transform: rotateY(720deg) rotateZ(-45deg) translate(-20px, 20px);
    opacity: 0;
  }
}

#section7 a {
  padding-top: 80px;
}
#section7 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  transform: rotate(-45deg);
  animation: sdb07 2s infinite;
  opacity: 0;
  box-sizing: border-box;
}
#section7 a span:nth-of-type(1) {
  animation-delay: 0s;
}
#section7 a span:nth-of-type(2) {
  top: 16px;
  animation-delay: 0.15s;
}
#section7 a span:nth-of-type(3) {
  top: 32px;
  animation-delay: 0.3s;
}

@keyframes sdb07 {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}

#section8 a {
  padding-top: 60px;
}
#section8 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 30px;
  height: 50px;
  margin-left: -15px;
  border: 2px solid #fff;
  border-radius: 50px;
  box-sizing: border-box;
}
#section8 a span::before {
  position: absolute;
  top: 10px;
  left: 50%;
  content: "";
  width: 6px;
  height: 6px;
  margin-left: -3px;
  background-color: #fff;
  border-radius: 100%;
  box-sizing: border-box;
}

#section9 a {
  padding-top: 60px;
}
#section9 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 30px;
  height: 50px;
  margin-left: -15px;
  border: 2px solid #fff;
  border-radius: 50px;
  box-sizing: border-box;
}
#section9 a span::before {
  position: absolute;
  top: 10px;
  left: 50%;
  content: "";
  width: 6px;
  height: 6px;
  margin-left: -3px;
  background-color: #fff;
  border-radius: 100%;
  animation: sdb9 2s infinite;
  box-sizing: border-box;
}
@keyframes sdb9 {
  0% {
    transform: translate(0, 0);
    opacity: 0;
  }
  40% {
    opacity: 1;
  }
  80% {
    transform: translate(0, 20px);
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}

#section10 a span {
  width: 35px;
  height: 50px;
  border: 3px solid #fff;
  border-top-left-radius: 40%;
  border-bottom-left-radius: 60%;
  border-top-right-radius: 40%;
  border-bottom-right-radius: 60%;
  position: absolute;
  top: -64px;
  left: -14%;
}

#section10 a span::before {
  content: "";
  position: absolute;
  width: 3px;
  height: 20px;
  background: #fff;
  top: 8px;
  left: 50%;
  transform: translateX(-50%);
  transform-origin: bottom;
  animation: for-before 3s infinite;
  transition: all 0.3s ease;
}

@keyframes for-before {
  0% {
    height: 0px;
  }
  60% {
    height: 20px;
  }
  60% {
    transform: scaleY(1) translateX(-50%);
  }
  100% {
    transform: scaleY(0) translateX(-50%);
  }
}

#section11 a span {
  background: rgba(255, 255, 255, 0.3);
  border-radius: 2px;
  bottom: 5px;
  height: 40px;
  top: -50px;
  left: 50%;
  overflow: hidden;
  position: absolute;
  transform: translateX(-50%);
  width: 4px;
}

#section11 a span::before {
  animation: pill 3.5s linear infinite;
  background-image: linear-gradient(to top, #fff 30%, #88d3ce 70%);
  border-radius: 2px;
  content: "";
  display: block;
  height: 40px;
  left: 0;
  position: absolute;
  width: 4px;
}

@keyframes line {
  0% {
    height: 0;
    top: 16px;
  }
  50% {
    height: 0;
    top: 16px;
  }
  55% {
    height: 5px;
    top: 16px;
  }
  100% {
    height: 30px;
    top: 66px;
  }
}

@keyframes circle {
  0% {
    height: 0;
    top: 0;
    width: 0;
  }
  50% {
    height: 16px;
    top: 0;
    width: 16px;
  }
  55% {
    height: 16px;
    top: 0;
    width: 16px;
  }
  100% {
    height: 0;
    top: 66px;
    width: 0;
  }
}

@keyframes pill {
  0% {
    top: -40px;
  }
  50% {
    top: 0;
  }
  100% {
    top: 50px;
  }
} */
</style>
