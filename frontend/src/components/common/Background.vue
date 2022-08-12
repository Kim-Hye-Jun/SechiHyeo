<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="bg">
    <div class="star-field">
      <div class="layer"></div>
      <div class="layer"></div>
      <div class="layer"></div>
    </div>
    <div class="star" v-bind:style="{ boxShadow: result }"></div>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<style>
.bg {
  /* background: url(https://i.ibb.co/87GbbFP/2799006.jpg) no-repeat; */
  background: #0f0c29;
  background: -webkit-linear-gradient(to bottom, #24243e, #302b63, #0f0c29);
  background: linear-gradient(to bottom, #24243e, #302b63, #0f0c29);
  background-size: cover;
  height: 100vh;
  width: 100vw;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -3;
}
.bg:before {
  content: "";
  width: 100vw;
  height: 100vh;
  background: #000;
  position: absolute;
  z-index: -1;
  top: 0;
  left: 0;
  opacity: 0.3;
}
@keyframes sf-fly-by-1 {
  from {
    transform: translateZ(-600px);
    opacity: 0.5;
  }
  to {
    transform: translateZ(0);
    opacity: 0.5;
  }
}
@keyframes sf-fly-by-2 {
  from {
    transform: translateZ(-1200px);
    opacity: 0.5;
  }
  to {
    transform: translateZ(-600px);
    opacity: 0.5;
  }
}
@keyframes sf-fly-by-3 {
  from {
    transform: translateZ(-1800px);
    opacity: 0.5;
  }
  to {
    transform: translateZ(-1200px);
    opacity: 0.5;
  }
}
.star-field {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  perspective: 1000px;
  -webkit-perspective: 1000px;
  z-index: -1;
}
.star-field .layer {
  box-shadow: -411px -476px #cccccc, 777px -407px #d4d4d4, -387px -477px #fcfcfc,
    -91px -235px #d4d4d4, 491px -460px #f7f7f7, 892px -128px #f7f7f7,
    758px -277px #ededed, 596px 378px #cccccc, 647px 423px whitesmoke,
    183px 389px #c7c7c7, 524px -237px #f0f0f0, 679px -535px #e3e3e3,
    158px 399px #ededed, 157px 249px #ededed, 81px -450px #ebebeb,
    719px -360px #c2c2c2, -499px 473px #e8e8e8, -158px -349px #d4d4d4,
    870px -134px #cfcfcf, 446px 404px #c2c2c2, 440px 490px #d4d4d4,
    414px 507px #e6e6e6, -12px 246px #fcfcfc, -384px 369px #e3e3e3,
    641px -413px #fcfcfc, 822px 516px #dbdbdb, 449px 132px #c2c2c2,
    727px 146px #f7f7f7, -315px -488px #e6e6e6, 952px -70px #e3e3e3,
    -869px -29px #dbdbdb, 502px 80px #dedede, 764px 342px #e0e0e0,
    -150px -380px #dbdbdb, 654px -426px #e3e3e3, -325px -263px #c2c2c2,
    755px -447px #c7c7c7, 729px -177px #c2c2c2, -682px -391px #e6e6e6,
    554px -176px #ededed, -85px -428px #d9d9d9, 714px 55px #e8e8e8,
    359px -285px #cfcfcf, -362px -508px #dedede, 468px -265px #fcfcfc,
    74px -500px #c7c7c7, -514px 383px #dbdbdb, 730px -92px #cfcfcf,
    -112px 287px #c9c9c9, -853px 79px #d6d6d6, 828px 475px #d6d6d6,
    -681px 13px #fafafa, -176px 209px #f0f0f0, 758px 457px #fafafa,
    -383px -454px #ededed, 813px 179px #d1d1d1, 608px 98px whitesmoke,
    -860px -65px #c4c4c4, -572px 272px #f7f7f7, 459px 533px #fcfcfc,
    624px -481px #e6e6e6, 790px 477px #dedede, 731px -403px #ededed,
    70px -534px #cccccc, -23px 510px #cfcfcf, -652px -237px whitesmoke,
    -690px 367px #d1d1d1, 810px 536px #d1d1d1, 774px 293px #c9c9c9,
    -362px 97px #c2c2c2, 563px 47px #dedede, 313px 475px #e0e0e0,
    839px -491px #e3e3e3, -217px 377px #d4d4d4, -581px 239px #c2c2c2,
    -857px 72px #cccccc, -23px 340px #dedede, -837px 246px white,
    170px -502px #cfcfcf, 822px -443px #e0e0e0, 795px 497px #e0e0e0,
    -814px -337px #cfcfcf, 206px -339px #f2f2f2, -779px 108px #e6e6e6,
    808px 2px #d4d4d4, 665px 41px #d4d4d4, -564px 64px #cccccc,
    -380px 74px #cfcfcf, -369px -60px #f7f7f7, 47px -495px #e3e3e3,
    -383px 368px #f7f7f7, 419px 288px #d1d1d1, -598px -50px #c2c2c2,
    -833px 187px #c4c4c4, 378px 325px whitesmoke, -703px 375px #d6d6d6,
    392px 520px #d9d9d9, -492px -60px #c4c4c4, 759px 288px #ebebeb,
    98px -412px #c4c4c4, -911px -277px #c9c9c9;
  transform-style: preserve-3d;
  position: absolute;
  top: 50%;
  left: 50%;
  height: 7px;
  width: 7px;
  filter: blur(1px);
  border-radius: 50px;
}
.star-field .layer:nth-child(1) {
  animation: sf-fly-by-1 5s linear infinite;
}
.star-field .layer:nth-child(2) {
  animation: sf-fly-by-2 5s linear infinite;
}
.star-field .layer:nth-child(3) {
  animation: sf-fly-by-3 5s linear infinite;
}
.star {
  position: fixed;
  top: 50%;
  left: 50%;
  height: 3px;
  width: 3px;
  filter: blur(1px);
  background-color: rgb(255, 255, 255);
  border-radius: 50px;
  box-shadow: -18vw 7vh 1px 1px #f9f34c, -43vw -22vh 0px 1px #f9f34c,
    -7vw 20vh 0.5px 0.5px #f9f34c, -29vw -8vh 0.5px 1px #f9f34c,
    -5vw 20vh 0.5px 1px #fcfff4, -45vw -30vh 0.5px 0px #fcfff4,
    -18vw 41vh 0px 0px #f9f34c, 2vw -47vh 0px 0.5px #f9f34c,
    15vw 7vh 0.5px 0.5px #b0d9f0, -6vw 10vh 1px 0.5px #f9f34c,
    -13vw -23vh 0.5px 0.5px #fcfff4, 6vw -44vh 1px 0px #fcfff4,
    47vw 15vh 1px 0.5px #b1ec14, 50vw -42vh 0.5px 0.5px #f9f34c,
    -43vw 38vh 1px 0px #d5cf93, 48vw 44vh 1px 0.5px #f9f34c,
    21vw 39vh 0.5px 1px #accbaa, -38vw -3vh 1px 0px #fcfff4,
    4vw -13vh 0.5px 0px #f9f34c, -35vw 9vh 0px 0.5px #f9f34c,
    10vw 15vh 0.5px 0.5px #f9f34c, -33vw 43vh 1px 0px #f9f34c,
    1vw 40vh 0.5px 0.5px #f9f34c, 37vw -50vh 0.5px 0.5px #d5cf93,
    42vw 40vh 1px 1px #dfe183, 30vw 6vh 0.5px 0.5px #f9f34c,
    -4vw -35vh 1px 0px #f9f34c, 43vw 7vh 0.5px 1px #f9eca3,
    -11vw -28vh 0.5px 0.5px #9d786a, 50vw 30vh 1px 1px #cdae7b,
    35vw 42vh 0px 1px #76858e, -31vw -44vh 0.5px 1px #fcfff4,
    13vw -6vh 0px 1px #fcfff4, 30vw 38vh 0px 0.5px #c4d2e3,
    -23vw -19vh 0.5px 0px #fcfff4, -30vw 3vh 0px 0px #a99674,
    -17vw 1vh 0.5px 0.5px #d5cf93, -6vw 19vh 0.5px 0px #c4d2e3,
    -24vw 42vh 0.5px 0.5px #d5cf93, -24vw 0vh 0.5px 0.5px #c4d2e3,
    38vw -21vh 0.5px 1px #fcfff4, -31vw -1vh 1px 1px #dfe7ef,
    -35vw -40vh 0px 0px #d5cf93, 44vw 29vh 0.5px 0.5px #c4d2e3,
    -9vw -30vh 1px 0.5px #d5cf93, -21vw 46vh 1px 1px #c4d2e3,
    -1vw -44vh 1px 0.5px #80bb5c, 46vw -20vh 1px 0.5px #d5cf93,
    -1vw -38vh 0.5px 1px #fcfff4, -41vw -20vh 0px 0px #d5cf93,
    -35vw 38vh 0.5px 1px #fcfff4, 17vw 47vh 0.5px 0px #b7e4ca,
    37vw 21vh 1px 1px #c4d2e3, -29vw 19vh 0.5px 1px #fcfff4,
    10vw 21vh 0px 1px #c4d2e3, 38vw 7vh 0px 1px #a5b6d3,
    -29vw -13vh 0px 0px #fcfff4, -22vw 18vh 1px 0px #d5cf93,
    -38vw -15vh 0px 1px #d89095, -16vw -38vh 0.5px 0.5px #d5cf93,
    48vw -38vh 1px 1px #d5cf93, -12vw -16vh 0.5px 0.5px #d5cf93,
    32vw -35vh 0px 0.5px #d5cf93, -50vw 14vh 0.5px 0px #d5cf93,
    3vw -35vh 0px 0.5px #c4d2e3, -18vw -18vh 0.5px 0.5px #d5cf93,
    -38vw 12vh 0.5px 0.5px #9bce9d, -2vw 0vh 0.5px 0px #d5cf93,
    44vw 37vh 0.5px 0px #fcfff4, 8vw 29vh 0.5px 1px #c4d2e3,
    32vw -45vh 0px 1px #feb6af, 24vw 38vh 0.5px 1px #d5cf93,
    32vw 25vh 0.5px 0.5px #feb6af, 33vw 22vh 0.5px 0.5px #fcfff4,
    -40vw 1vh 0px 0.5px #d5cf93, -48vw 13vh 0.5px 0.5px #fcfff4,
    46vw -29vh 0.5px 0px #fcfff4, -11vw 39vh 1px 0.5px #fcfff4,
    -28vw 20vh 0.5px 0.5px #d89095, 20vw 13vh 0.5px 0.5px #fcfff4,
    15vw -22vh 0px 0px #fcfff4, -20vw -49vh 0.5px 1px #fcfff4,
    19vw 24vh 0.5px 1px #feb6af, -37vw -39vh 1px 0px #fcfff4,
    -33vw -25vh 1px 0.5px #fcfff4, -20vw -33vh 0.5px 0.5px #fcfff4,
    -2vw 4vh 0.5px 0px #feb6af, -10vw -41vh 1px 0.5px #fcfff4,
    25vw -4vh 0px 0.5px #fcfff4, -42vw 50vh 0.5px 1px #feb6af,
    -22vw 31vh 0.5px 0.5px #feb6af, 32vw 37vh 0.5px 0.5px #fcfff4,
    3vw -17vh 0.5px 0.5px #b2cb27, -37vw -19vh 0px 0px #feb6af,
    -49vw 23vh 0px 0.5px #feb6af, -3vw 21vh 1px 0.5px #d89095,
    17vw 27vh 1px 0.5px #feb6af, 9vw -5vh 0.5px 1px #d89095,
    -48vw -6vh 0.5px 0.5px #afeb7a, -9vw -50vh 0px 0.5px #fcfff4,
    -28vw 5vh 1px 1px #d89095, 23vw -8vh 1px 0px #d6dd34,
    -10vw 13vh 0.5px 1px #fcfff4, -7vw -13vh 0px 0.5px #d5cf93,
    10vw -30vh 0.5px 1px #feb6af, -7vw 31vh 1px 0.5px #feb6af,
    -19vw -35vh 0.5px 1px #feb6af, -21vw -1vh 0.5px 0px #d89095,
    11vw -32vh 0.5px 0.5px #fcfff4, -2vw -49vh 0.5px 1px #feb6af,
    -49vw -2vh 0px 1px #fcfff4, -35vw -8vh 0.5px 0.5px #d89095,
    49vw 34vh 1px 1px #fcfff4, -33vw 36vh 1px 0px #c7cc67,
    -34vw 7vh 0px 0px #afb529, 43vw -4vh 0.5px 0.5px #afb529,
    -43vw 50vh 0.5px 1px #afb529, -16vw -24vh 0.5px 0.5px #c8c9b2,
    8vw 37vh 1px 1px #fcfff4, -24vw 34vh 1px 0px #6bd402,
    -36vw 5vh 0.5px 0px #c8c9b2, -9vw 33vh 0px 0.5px #fcfff4,
    -24vw 11vh 0.5px 0px #c8c9b2, 38vw -6vh 0px 0px #fcfff4,
    -49vw 19vh 0.5px 0.5px #afb529, -34vw 14vh 0.5px 0px #afb529,
    -7vw -16vh 0px 1px #c8c9b2, -38vw -41vh 0.5px 1px #b2db76,
    -47vw 19vh 1px 0.5px #e4e9a5, 17vw -6vh 0px 0.5px #fcfff4,
    20vw 28vh 1px 0.5px #afb529, 49vw 14vh 0px 0.5px #a0b596,
    2vw 45vh 1px 0.5px #93870b, 4vw 35vh 0.5px 1px #afb529,
    -31vw 37vh 1px 0.5px #f8f4e5, 43vw 12vh 1px 0.5px #a2eaca,
    -11vw -12vh 0.5px 0.5px #afb529, -29vw -50vh 0.5px 1px #f8f4e5,
    -26vw -18vh 0.5px 1px #fcfff4, 33vw 2vh 0.5px 0.5px #fcfff4,
    17vw 40vh 1px 1px #f8f4e5, 29vw -10vh 1px 0px #c8c9b2,
    -42vw 17vh 1px 0.5px #c8c9b2, 10vw 22vh 1px 0.5px #fcfff4,
    -33vw 29vh 0.5px 1px #c8c9b2, 9vw -11vh 0.5px 0.5px #ececee,
    -43vw -5vh 0.5px 0px #f8f4e5, 50vw -41vh 1px 0.5px #bffade,
    -37vw 37vh 1px 1px #fcfff4, -35vw -15vh 1px 0.5px #d0df4e,
    17vw -34vh 0px 1px #e3f661, 17vw -35vh 1px 1px #fcfff4,
    -20vw 0vh 0.5px 0px #e3f661, 7vw -31vh 0px 1px #fcfff4,
    44vw 30vh 0.5px 0px #88bc3e, 31vw 1vh 0px 0.5px #ccd846,
    -30vw 36vh 0px 0px #e3f661, 2vw 13vh 0px 0.5px #fcfff4,
    33vw 6vh 0.5px 0.5px #f8f4e5, 14vw 35vh 0px 0px #98c925,
    18vw 10vh 0.5px 0.5px #f8f4e5, 27vw -2vh 0px 0px #e3f661,
    22vw -3vh 0.5px 0px #fcfff4, 9vw 44vh 1px 0px #e3f661,
    15vw 28vh 0px 0.5px #e3f661, -37vw 27vh 0.5px 0.5px #c8c9b2,
    34vw -32vh 0px 0.5px #fcfff4, 15vw -13vh 0.5px 0px #e3f661,
    -4vw -48vh 0.5px 0.5px #c8c9b2, -37vw -32vh 1px 0.5px #e3f661,
    -33vw 14vh 0px 1px #f8f4e5, 15vw -6vh 0.5px 0px #f8f4e5,
    0vw -35vh 0px 1px #fcfff4, -5vw 10vh 1px 0.5px #e3f661,
    47vw 5vh 0px 1px #f8f4e5, -28vw 21vh 0.5px 0.5px #fcfff4,
    18vw 17vh 0.5px 0px #c8c9b2, -3vw -28vh 0.5px 0.5px #f8f4e5,
    -37vw -40vh 1px 0px #c8c9b2, 46vw -41vh 0.5px 0.5px #f8f4e5,
    26vw 14vh 0.5px 0px #fcfff4, 30vw -20vh 1px 0px #fcfff4,
    -37vw 20vh 0.5px 0.5px #f8f4e5, 24vw -50vh 0.5px 1px #f8f4e5,
    45vw -38vh 0.5px 0.5px #fcfff4, -11vw 28vh 1px 0.5px #e9a884,
    23vw -23vh 1px 0px #c8c9b2, 6vw -43vh 0px 1px #f8f4e5;
}
</style>
