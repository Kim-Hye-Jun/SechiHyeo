<template>
  <div class="container">
    <form autocomplete="off">
      <div class="finder">
        <div class="finder__outer">
          <div class="finder__inner">
            <div class="finder__icon" ref="icon"></div>
            <input
              class="finder__input"
              type="text"
              name="q"
              v-model="searchInputValue"
              @keyup.enter="searchRooms"
            />
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  data() {
    return {
      searchInputValue: "",
    };
  },
  methods: {
    searchRooms(): void {
      this.$emit("getSearchValue", this.searchInputValue);
      this.searchInputValue = "";
    },
    addEventListener(): void {
      const input: HTMLInputElement | null =
        document.querySelector(".finder__input");
      const finder: HTMLDivElement | null = document.querySelector(".finder");
      const form: HTMLFormElement | null = document.querySelector("form");

      input?.addEventListener("focus", () => {
        finder?.classList.add("active");
      });

      input?.addEventListener("blur", () => {
        if (input?.value.length === 0) {
          finder?.classList.remove("active");
        }
      });

      form?.addEventListener("submit", (ev) => {
        ev.preventDefault();
        finder?.classList.add("processing");
        finder?.classList.remove("active");
        // input?.disabled = true;
        setTimeout(() => {
          finder?.classList.remove("processing");
          if (input) {
            input.disabled = false;
            if (input.value.length > 0) {
              finder?.classList.add("active");
            }
          }
        }, 1000);
      });
    },
  },
  mounted() {
    this.addEventListener();
  },
});
</script>

<style lang="scss" scoped>
.container {
  text-align: center;
  color: #2c3e50;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

// form {
//   transition: all 0.5s;
//   position: absolute;
//   top: 50%;
//   transform: translateY(-50%);
// }
.finder {
  border: 0.1em solid #fff;
  background-color: #f6f5f0;
  border-radius: 1em;
  padding: 0.5em;
  box-shadow: 0.55em 0.55em 1em rgba(189, 189, 189, 0.6),
    -0.55em -0.55em 1em rgba(255, 255, 255, 0.5);
}

.finder__outer {
  display: flex;
  width: 50em;
  padding: 1.5rem 2rem;
  border-radius: 1em;
  box-shadow: inset 0.7em 0.7em 1em -1em #c3c3c3,
    inset -0.7em -0.7em 1em -0.7em #ffffff;
}

.finder__inner {
  display: flex;
  align-items: center;
  position: relative;
  flex: 1;
}

.finder__form {
  flex: 1;
  height: calc(100% + 30rem);
}

.finder__input {
  width: 30em;
  // height: calc(100% + 3rem);
  border: none;
  background-color: transparent;
  outline: none;
  font-size: 1.5rem;
  letter-spacing: 0.75px;
}

.finder__icon {
  width: 2.5em;
  height: 2.5em;
  margin-right: 1rem;
  transition: all 0.2s;
  box-shadow: inset 0 0 0 20px #292929;
  border-radius: 50%;
  position: relative;

  &:after,
  &:before {
    display: block;
    content: "";
    position: absolute;
    transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
  }

  &:after {
    width: 0.7em;
    height: 0.7em;
    background-color: #292929;
    border: 0.1em solid #f6f5f0;
    top: 50%;
    position: absolute;
    transform: translateY(-50%);
    left: 0px;
    right: 0;
    margin: auto;
    border-radius: 50%;

    @at-root .active & {
      border-width: 10px;
      background-color: #f6f5f0;
    }
  }

  &:before {
    width: 0.2em;
    height: 1em;
    background-color: #f6f5f0;
    top: 50%;
    left: 20px;
    transform: rotateZ(45deg) translate(-50%, 0);
    transform-origin: 0 0;
    border-radius: 4px;

    @at-root .active & {
      background-color: #292929;
      width: 6px;
      transform: rotateZ(45deg) translate(-50%, 25px);
    }
  }

  @at-root .processing & {
    transform-origin: 50%;
    animation: spinner 0.3s linear infinite;
    animation-delay: 0.5s;
  }

  @at-root .active & {
    transform: translateY(-5px);
  }
}

@keyframes spinner {
  0% {
    transform: rotateZ(45deg);
  }
  100% {
    transform: rotateZ(405deg);
  }
}
</style>
