<template>
  <div class="file-upload">
    <form @submit.prevent="formSubmit">
      <input
        type="file"
        ref="selectFile"
        @change="previewFile"
        class="userpage-content-set-form-fix"
      />
      <div class="userpage-content-set-form-image">
        <img
          v-if="previewImgUrl"
          :src="previewImgUrl"
          style="width: 150px; height: 150px"
        />
      </div>
      <button type="submit" :disabled="isUploading">Upload</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import http from "@/http/index";
import { mapActions } from "vuex";
export default defineComponent({
  components: {},
  data() {
    return {
      name: "FormValidation",
      selectFile: null,
      previewImgUrl: null,
      isUploading: false,
      response: null,
    };
  },
  methods: {
    ...mapActions(["PROFILEIMAGE"]),
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
        } else {
          alert("파일을 다시 선택해 주세요.");
          this.selectFile = null;
          this.previewImgUrl = null;
        }
      } else {
        this.selectFile = null;
        this.previewImgUrl = null;
      }
      // console.log(this.selectFile);
    },
    async formSubmit() {
      if (this.selectFile) {
        let form = new FormData();
        form.append("file", this.selectFile);
        this.isUploading = true;
        // http
        //   // 추후 주소 수정
        //   .put("https://i7a508.p.ssafy.io/api/member/profile-image", form, {
        //     headers: {
        //       "Content-Type": "multipart/form-data",
        //     },
        //   })
        this.PROFILEIMAGE()
          .then((res: any) => {
            this.response = res;
            this.isUploading = false;
          })
          .catch((error: any) => {
            this.response = error;
            this.isUploading = false;
          });
      } else {
        alert("파일을 선택해 주세요.");
      }
      return true;
    },
  },
});
</script>

<style scoped></style>
