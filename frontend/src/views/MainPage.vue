<template>
  <div id="contents">
    <div id="section1">
      <a href="#section11" class="scroll">DOWN<span></span></a>
    </div>
    <div id="section11">
      <div class="container">
        <p>이 화면은 메인 화면 입니다</p>
        <br />
        <p>토론을 즐겨보세요</p>
        <a href="#section1" class="scroll">UP<span></span></a>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";

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

p {
  font-size: 40px;
  font-weight: 500;
  text-transform: uppercase;
  /* font-style: italic; */
}

#section1 {
  height: 100vh;
  width: 100vw;

  background: #000000;
  background-image: url("@/assets/logo.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: 60% 60%;
  /* background-color: transparent; */
}
/* scroll */
.scroll {
  position: absolute;
  bottom: 30px;
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

#section1 a {
  padding-top: 60px;
}
#section1 a span {
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
#section1 a span::after {
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
#section1 a span::before {
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

#section11 {
  background: black;
}

#section11 a {
  padding-top: 55px;
  /* padding-right: 30px; */
}
#section11 a span {
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
#section11 a span::after {
  position: absolute;
  top: 60%;
  left: 50%;
  content: "";
  width: 16px;
  height: 16px;
  margin: -12px 0 0 -8px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  -webkit-transform: rotate(135deg);
  transform: rotate(135deg);
  box-sizing: border-box;
}
#section11 a span::before {
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
</style>
