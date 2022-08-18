<template>
  <background></background>
  <div class="flex">
    <!-- <room-entrance-search-input-component
      @getSearchValue="updateRooms"
    ></room-entrance-search-input-component> -->
    <div class="sus">
      <suspense>
        <room-entrance-board-component
          :pageNum="nowPage"
        ></room-entrance-board-component>
      </suspense>
    </div>
    <nav id="pagenation-bar" aria-label="Page navigation example" class="navPg">
      <!-- <ul class="pagination">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li v-for="index in pageCount" :key="index" class="page-item">
          <a class="page-link" href="#">{{ index }}</a>
        </li>

        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul> -->
      <div class="btn-cover">
        <button
          :disabled="nowPage === 0"
          @click="prevPage"
          class="btn btn-outline-secondary border-radius"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-chevron-left"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"
            />
          </svg>
        </button>
        <span class="page-count"
          >&nbsp;{{ nowPage + 1 }} / {{ pageCount }} &nbsp;</span
        >
        <button
          :disabled="nowPage >= pageCount - 1"
          @click="nextPage"
          class="btn btn-outline-secondary border-radius"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-chevron-right"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"
            />
          </svg>
        </button>
      </div>
    </nav>
  </div>
  <button
    id="createButton"
    type="button"
    class="btn btn-primary btn-lg"
    data-bs-toggle="modal"
    data-bs-target="#create"
    @click="modalIn"
  >
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="20"
      height="20"
      fill="currentColor"
      class="bi bi-plus-circle-fill"
      viewBox="0 0 16 16"
    >
      <path
        d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"
      />
    </svg>
    방 생성
  </button>

  <!-- 모달창 -->
  <div id="create" class="modal fade">
    <div class="modal-dialog modal-dialog-scrollable modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="text">방 만들기</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <modal-input-box-component
            ref="roomTitleInput"
            :labelName="roomTitleLabelName"
          ></modal-input-box-component>
          <modal-input-box-component
            ref="debateSubjectInput"
            :labelName="debateTopicLabelName"
          ></modal-input-box-component>
          <div class="flex__modal__side">
            <modal-input-box-component
              ref="sideAInput"
              :labelName="sideALabelName"
            ></modal-input-box-component>
            <modal-input-box-component
              ref="sideBInput"
              :labelName="sideBLabelName"
            ></modal-input-box-component>
          </div>
          <!--방 이미지 업로드 -->
          <div>
            <input type="file" ref="selectFile" @change="previewFile" />
            <div id="preview">
              <img
                v-if="previewImgUrl"
                :src="previewImgUrl"
                style="width: 150px; height: 150px"
              />
            </div>
          </div>
          <div class="flex__ul">
            <modal-radio-button-component
              labelId="private"
              labelValue="private"
              :selector="accessModifierRadioSelecter"
            ></modal-radio-button-component>
            <modal-radio-button-component
              labelId="public"
              labelValue="public"
              :selector="accessModifierRadioSelecter"
            ></modal-radio-button-component>
          </div>
          <div class="flex__ul">
            <modal-radio-button-component
              labelId="2"
              labelValue="2"
              :selector="numOfPeopleRadioSelecter"
            ></modal-radio-button-component>
            <modal-radio-button-component
              labelId="4"
              labelValue="4"
              :selector="numOfPeopleRadioSelecter"
            ></modal-radio-button-component>
            <modal-radio-button-component
              labelId="6"
              labelValue="6"
              :selector="numOfPeopleRadioSelecter"
            ></modal-radio-button-component>
          </div>
        </div>

        <div class="modal-footer">
          <button-component-vue
            data-bs-dismiss="modal"
            @click="myFunc()"
            :buttonName="createButton"
          ></button-component-vue>
          <button-component-vue
            data-bs-dismiss="modal"
            :buttonName="backButton"
          ></button-component-vue>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, toRefs, reactive } from "vue";
import RoomEntranceBoardComponent from "@components/organisms/room-entrance/RoomEntranceBoardComponent.vue";
import RoomEntranceSearchInputComponent from "@components/atoms/room-entrance/RoomEntranceSearchInputComponent.vue";
import ButtonComponent from "@/components/atoms/common/ButtonComponent2.vue";
import ButtonComponentVue from "@/components/atoms/common/ButtonComponent.vue";
import ButtonComponentBack from "@/components/atoms/common/ButtonComponentBack.vue";
import ModalInputBoxComponent from "@/components/molecules/room-entrance/ModalInputBoxComponent.vue";
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";
import RoomMakeModalComponent from "@/components/organisms/room-entrance/RoomMakeModalComponent.vue";
import { RoomCreateRequestInfo, RoomCreateResponseInfo } from "@type/types";
import { useStore } from "vuex";

import http from "@/http";
import { hasChanged } from "@vue/shared";
import Background from "@/components/common/Background.vue";

export default defineComponent({
  components: {
    RoomEntranceBoardComponent,
    // RoomEntranceSearchInputComponent,
    // ButtonComponent,
    // RoomMakeModalComponent,
    ButtonComponentVue,
    // ButtonComponentBack,
    ModalInputBoxComponent,
    ModalRadioButtonComponent,
    Background,
  },
  setup() {
    let state = reactive({
      isRoomMakeModalView: false,
    });

    const store = useStore();

    return {
      state,
      store,
    };
  },

  created() {
    http.get("sessions/pageNum").then((res) => {
      this.roomCount = res.data;
      this.pageCount = Math.floor(this.roomCount / 6) + 1;
    });
  },

  data() {
    return {
      modal: false,
      previewImgUrl: null as any,
      selectFile: null,
      roomTitleLabelName: "RoomTitle" as string,
      debateTopicLabelName: "DebateTopic" as string,
      sideALabelName: "Side 1" as string,
      sideBLabelName: "Side 2" as string,
      accessModifierRadioSelecter: "accessModifier" as string,
      numOfPeopleRadioSelecter: "numOfPeople" as string,
      roomCount: 1 as number,
      pageCount: 1 as number,
      nowPage: 0 as number,
      createButton: "CREATE",
      backButton: "BACK",
    };
  },

  methods: {
    openModal() {
      document.location.href = "#open-modal";
    },
    updateRooms(searchValue: string) {
      //(#구현해야할것) searchValue로 방검색 axios 요청 보낸 후 room-entrance-board-component 갱신
    },
    nextPage() {
      this.nowPage += 1;
    },
    prevPage() {
      this.nowPage -= 1;
    },
    // onFileChange(e: any) {
    //   const file = e.target.files[0];
    //   this.url = URL.createObjectURL(file);
    // },
    myFunc(): void {
      if (
        document.querySelector(
          `input[name=${this.accessModifierRadioSelecter}]:checked`
        ) === null ||
        document.querySelector(
          `input[name=${this.accessModifierRadioSelecter}]:checked`
        ) === null
      ) {
        console.log("NULL ...!");

        return;
      }

      const roomCreateRequestValue: RoomCreateRequestInfo = {
        roomTitle: (
          this.$refs.roomTitleInput as InstanceType<
            typeof ModalInputBoxComponent
          >
        ).inputValue,
        debateTopic: (
          this.$refs.debateSubjectInput as InstanceType<
            typeof ModalInputBoxComponent
          >
        ).inputValue,
        headCount: parseInt(
          (
            document.querySelector(
              `input[name=${this.numOfPeopleRadioSelecter}]:checked`
            ) as HTMLInputElement
          ).getAttribute("value") as string
        ),
        roomType: (
          document.querySelector(
            `input[name=${this.accessModifierRadioSelecter}]:checked`
          ) as HTMLInputElement
        ).getAttribute("value") as string,
        debateType: "토론유형",
        sideA: (
          this.$refs.sideAInput as InstanceType<typeof ModalInputBoxComponent>
        ).inputValue,
        sideB: (
          this.$refs.sideBInput as InstanceType<typeof ModalInputBoxComponent>
        ).inputValue,
      };

      // (#구현할것) 모달 input에서 데이터를 받아와서 방 생성 post 요청을 보냄
      console.log("ROOM CREATE REQUEST VALUE", roomCreateRequestValue);
      http
        .post("/sessions", roomCreateRequestValue)
        .then((res) => {
          console.log("ROOM CREATE RES : ", res);
          if (res.status === 200) {
            const roomId = res.data.roomId;
            console.log(roomId);
            if (this.selectFile) {
              let thumbnail = new FormData();
              thumbnail.append("thumbnail", this.selectFile);
              http
                .post(
                  "https://i7a508.p.ssafy.io/api/sessions/" +
                    roomId +
                    "/thumbnail",
                  thumbnail,
                  {
                    headers: {
                      "Content-Type": "multipart/form-data",
                      "access-token": this.store.state.token,
                    },
                  }
                )
                .then((res) => {
                  console.log(res);
                  console.log("썸네일 업로드 완료");
                  this.goRoomInsidePage(roomId);
                })
                .catch(() => {
                  console.log("썸네일 업로드에 실패했습니다.");
                });
            } else {
              this.goRoomInsidePage(roomId);
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
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
          ["jpeg", "png", "gif", "bmp", "jpg"].includes(fileExt) &&
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
    goRoomInsidePage(roomId: string): void {
      // this.modalOut();
      console.log("GO ROOM INSIDE....");
      this.$router.push({
        name: "RoomInsidePage",
        params: {
          roomId: roomId,
          side: "a",
          order: 1,
        },
      });
    },
    modalIn() {
      this.modal = true;
    },
    modalOut() {
      this.modal = false;
    },
  },
});
</script>

<style scoped>
body {
  background: rgb(7, 8, 78);
}

.sus {
  position: relative;
}

.navPg {
  position: relative;
  margin-top: 20px;
}

.modal-content {
  background: rgb(46, 37, 37);
  margin-top: 10%;
}

.modal-footer {
  justify-content: center;
}

.flex {
  margin-top: 4%;
  margin-left: 2.5%;
  width: 95%;
  height: 80%;
}

.flex__ul {
  display: flex;
  flex-direction: row;
}
.flex__modal__side {
  display: flex;
  flex-direction: row;
  width: 50%;
}
.text {
  color: white;
}

#createButton {
  position: absolute;
  z-index: 99;
  top: 90%;
  left: 90%;
}

#pagenation-bar {
  position: absolute;
  z-index: 99;
  top: 90%;
  left: 50%;
  transform: translate(-50%, 0%);

  bottom: 0; /* 하단에 여백 없이 */
  width: 100%; /* 가로 사이즈를 브라우저에 가득 채움 */
  text-align: center;
}

.page-count {
  color: aliceblue;
}
</style>
