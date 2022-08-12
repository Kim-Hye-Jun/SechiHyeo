<template>
  <div class="file-upload">
    <form @submit.prevent="formSubmit" method="post">
      <input type="file" ref="selectFile" @change="previewFile" />
      <img
        v-if="previewImgUrl"
        :src="previewImgUrl"
        style="width: 300px; height: 300px"
      />
      <button type="submit" :disabled="isUploading">Upload</button>
    </form>
  </div>
</template>

<script>
import http from "@/http/index";

export default {
  name: "FormValidation",
  components: {},
  data() {
    return {
      selectFile: null,
      previewImgUrl: null,
      isUploading: false,
      response: null,
    };
  },
  methods: {
    previewFile() {
      if (0 < this.$refs.selectFile.files.length) {
        this.selectFile = this.$refs.selectFile.files[0];
        let fileExt = this.selectFile.name.substring(
          this.selectFile.name.lastIndexOf(".") + 1
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
        } else if (this.selectFile.size <= 1048576) {
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
    async formSubmit() {
      if (this.selectFile) {
        let form = new FormData();
        form.append("file", this.selectFile);
        this.isUploading = true;
        http
          // 주소 수정
          .post("/api/sample/upload/", form, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((res) => {
            this.response = res;
            this.isUploading = false;
          })
          .catch((error) => {
            this.response = error;
            this.isUploading = false;
          });
      } else {
        alert("파일을 선택해 주세요.");
      }
      return true;
    },
  },
};
</script>

<style scoped></style>
