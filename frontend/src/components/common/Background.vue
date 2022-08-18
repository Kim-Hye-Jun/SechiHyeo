<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="bg">
    <div class="star"></div>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<style scoped>
.bg {
  background: #0f0c29;
  background: -webkit-linear-gradient(to bottom, #24243e, #302b63, #0f0c29);
  background: linear-gradient(to bottom, #24243e, #302b63, #0f0c29);
  background-size: cover;
  /* height: 100vh;
  width: 100vw; */
  height: 100%;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: -3;
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
