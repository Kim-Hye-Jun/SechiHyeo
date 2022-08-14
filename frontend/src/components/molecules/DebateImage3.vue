<template>
  <div class="c">
    <div>
      <form>
        <div style="height: 565px">
          <img
            v-if="previewImgUrl"
            :src="previewImgUrl"
            style="
              max-width: 730px;
              max-height: 600px;
              margin-left: 50%;
              transform: translate(-50%);
              padding: 10px;
            "
          />
        </div>
      </form>
    </div>
    <input
      type="file"
      ref="selectFile"
      @change="previewFile"
      style="position: absolute; top: 100%"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  data() {
    return {
      selectFile: null,
      previewImgUrl: null,
      isUploading: false,
      response: null,
      data1: true,
      data2: false,
      data3: false,
    };
  },
  methods: {
    previewFile() {
      if (
        0 < ((this.$refs["selectFile"] as any)["files"]["length"] as number)
      ) {
        this.selectFile = (this.$refs["selectFile"] as any)["files"][0] as null;
        let fileExt = ((this.selectFile as any)["name"] as string).substring(
          ((this.selectFile as any)["name"] as string).lastIndexOf(".") + 1
        );
        fileExt = fileExt.toLowerCase();
        if (
          ["jpeg", "png", "gif", "bmp"].includes(fileExt) &&
          this.selectFile != null &&
          this.selectFile["size"] <= 1048576
        ) {
          var reader = new FileReader();
          reader.onload = (e: any) => {
            this.previewImgUrl = e.target.result;
          };
          reader.readAsDataURL(this.selectFile);
        } else if (((this.selectFile as any)["size"] as number) <= 1048576) {
          this.previewImgUrl = null;
        } else {
          alert("파일을 다시 선택해 주세요.");
          this.selectFile = null;
          this.previewImgUrl = null;
        }
      } else {
        this.selectFile = null;
        this.previewImgUrl = null;
      }
      console.log(this.selectFile);
    },
  },
});
</script>

<style scoped>
.hidden {
  display: none;
}
</style>
