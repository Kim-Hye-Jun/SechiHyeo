<template>
  <div class="file-upload">
    <form @submit.prevent="formSubmit">
      <div class="userpage-content-set-form-image">
        <img
          v-if="previewImgUrl"
          :src="previewImgUrl"
          style="width: 130px; height: 130px"
        />
      </div>
      <input
        type="file"
        ref="selectFile"
        @change="previewFile"
        class="userpage-content-set-form-fix"
        style="margin-left: 30px"
      />
      <button type="submit" :disabled="isUploading" style="margin-left: 10px">
        Upload
      </button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import http from "@/http/index";
import { mapActions, useStore } from "vuex";
export default defineComponent({
  components: {},
  setup() {
    const store = useStore();
    return { store };
  },
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
        let profileImage = new FormData();
        profileImage.append("profileImage", this.selectFile);
        this.isUploading = true;
        http
          .put(
            "https://i7a508.p.ssafy.io/api/member/profile-image",
            profileImage,
            {
              headers: {
                "Content-Type": "multipart/form-data",
                "access-token": this.store.state.token,
              },
            }
          )
          // this.PROFILEIMAGE(profileImage, {
          //   headers: {
          //     "Content-Type": "multipart/form-data",
          //   },
          // })
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

<style scoped>
.userpage-content-set-form-image {
  position: absolute;
  width: 130px;
  height: 130px;
  top: 10px;
  left: 40px;
  background: #000000;
  border: 3px solid rgba(0, 0, 0, 0.3);
  padding-right: 8px;
  padding-bottom: 20px;
  border-radius: 10px;
}
.userpage-content-set-form-fix {
  width: 80px;
  height: 30px;
  left: 100px;
  top: 500px;
  /* background: #070707;
  border-radius: 10px;
  border: #ffffff solid 1px;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  color: none; */
}
.userpage-content-set-form-fix:hover {
  background: #ff0080;
}
</style>
