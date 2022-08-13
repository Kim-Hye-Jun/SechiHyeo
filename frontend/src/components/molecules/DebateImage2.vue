<template>
  <div class="c">
    <div class="file-upload">
      <form @submit.prevent="formSubmit" method="post">
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
      style="position: absolute; top: 90%"
    />
  </div>
</template>

<script>
import { ref, defineComponent } from "vue";
import http from "@/http/index";
export default defineComponent({
  components: {},
  data() {
    return {
      name: "FormValidation",
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
      if (0 < this.$refs.selectFile.files.length) {
        this.selectFile = this.$refs.selectFile.files[0];
        let fileExt = this.selectFile?.name.substring(
          this.selectFile?.name.lastIndexOf(".") + 1
        );
        fileExt = fileExt.toLowerCase();
        if (
          ["jpeg", "png", "gif", "bmp"].includes(fileExt) &&
          this.selectFile.size <= 1048576
        ) {
          var reader = new FileReader();
          reader.onload = (e) => {
            this.previewImgUrl = e.target.result;
          };
          reader.readAsDataURL(this.selectFile);
        } else if (this.selectFile?.size <= 1048576) {
          this.previewImgUrl = null;
        } else {
          alert("파일 용량 초과입니다! 다른 파일을 선택해 주세요~.");
          this.selectFile = null;
          this.previewImgUrl = null;
        }
      } else {
        this.selectFile = null;
        this.previewImgUrl = null;
      }
      console.log(this.selectFile);
    },
    data1Click() {
      this.data1 = true;
      this.data2 = false;
      this.data3 = false;
      console.log(this.data1);
      console.log(this.data2);
      console.log(this.data3);
    },
    data2Click() {
      this.data1 = false;
      this.data2 = true;
      this.data3 = false;
      console.log(this.data1);
      console.log(this.data2);
      console.log(this.data3);
    },
    data3Click() {
      this.data1 = false;
      this.data2 = false;
      this.data3 = true;
      console.log(this.data1);
      console.log(this.data2);
      console.log(this.data3);
    },
  },
});
</script>

<style scoped>
.hidden {
  display: none;
}
</style>
