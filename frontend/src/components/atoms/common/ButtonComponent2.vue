<template>
  <button class="button raise">{{ buttonName }}</button>
</template>
<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  props: {
    buttonName: String,
  },
  components: {},
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
.raise:hover {
  box-shadow: 0 0.5em 0.5em -0.4em var(--hover);
  transform: translateY(-0.25em);
}

.raise {
  --color: #5dd6f1;
  --hover: #0dfbef;
}

.button {
  width: 10em;
  color: var(--color);
  transition: 0.25s;

  background: #000000;
  border: 2px solid;
  font: inherit;
  line-height: 1;
  margin: 0.5em;
  padding: 1em 2em;
}
.button:hover {
  cursor: pointer;
  border-color: var(--hover);
  color: #fff;
}
</style>
