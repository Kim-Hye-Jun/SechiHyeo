<template>
  <div
    class="userpage-content-room"
    v-for="board_number in myRecruitBoardList"
    :key="board_number"
  >
    <div class="userpage-content-count"></div>
    <div class="userpage-content-topic">
      {{ boardtopic(board_number) }}
    </div>
    <button class="apply-check">
      <!-- getApply(applicant_recruit['debate_topic']  이건 뭐여..-->
      승인대기
    </button>
    <!-- <button
      class="apply-check"
      :class="[apply_modal === true ? '' : 'hidden']"
      @click="modalHidden()"
    >
      숨기기
    </button> -->
    <!-- 박스 안에 해당 게시판 신청자 리스트 모달 -->
    <div class="apply-modal">
      <div v-for="apply in myBoardMemberList[board_number]" :key="apply">
        <div style="display: inline-block; position: relative">
          {{ apply.nickname }}
        </div>
        <!-- <div style="position: absolute; margin-left: 35px">
          <button class="apply-button" @click="putApply_no(apply.applicant_no)">
            거절
          </button>
          <button class="apply-button" @click="putApply_go(apply.applicant_no)">
            수락
          </button>
        </div> -->
      </div>
    </div>
    <!-- 토론방 이동 메서드 추후에 토론방 완성 후 작성 예정... -->
    <button
      class="userpage-content-room-button userpage-content-room-button-start"
      type="button"
      data-bs-toggle="modal"
      data-bs-target="#create2"
      @click="modalIn"
    >
      시작
      <!-- <a href="#" class="userpage-content-room-button-start" @click="modalIn"
        >시작</a
      > -->
      <!-- <path
        d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"
      /> -->
    </button>
  </div>
  <!-- 모달창 -->
  <div
    id="create2"
    class="modal fade"
    data-backdrop="false"
    style="
      top: -300px;
      width: 800px;
      height: 700px;
      margin-left: 50%;
      transform: translate(-50%);
    "
  >
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
import { defineComponent, reactive } from "vue";
import { mapActions, mapState, useStore } from "vuex";
import ModalInputBoxComponent from "@/components/molecules/room-entrance/ModalInputBoxComponent.vue";
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";
import ButtonComponentVue from "@/components/atoms/common/ButtonComponent.vue";
import ButtonComponentBack from "@/components/atoms/common/ButtonComponentBack.vue";
import http from "@/http";
import { RoomCreateRequestInfo } from "@type/types";
import axios from "axios";
import { API_BASE_URL } from "@/config";
export default defineComponent({
  components: {
    ModalInputBoxComponent,
    ModalRadioButtonComponent,
    ButtonComponentVue,
    // ButtonComponentBack,
  },
  data() {
    return {
      apply_modal: false,
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
      myRecruitBoardList: [] as number[],
      myBoardMemberList: [] as any[],
    };
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
  computed: {
    ...mapState([
      "board",
      "debate_board",
      "memberinfo",
      "applicant",
      "applicant_recruit",
      "applicant_recruit_array",
      "apply",
      "applies",
    ]),
  },
  created() {
    //내가 모집한 토론의 모든 지원자 호출(토론게시판 중복 되어도 전부)
    //applicant_recruit_array에 저장
    this.debateRecruit()
      .then(() => {
        const list = this.store.state.applicant_recruit_array;
        for (let i = 0; i < list.length; i++) {
          if (!this.myRecruitBoardList.includes(list[i].board_no)) {
            this.myRecruitBoardList.push(list[i].board_no);
          }
        }
        this.myRecruitBoardList.sort();
      })
      .then(() => {
        for (let i = 0; i < this.myRecruitBoardList.length; i++) {
          axios
            .get(`${API_BASE_URL}debate-apply/${this.myRecruitBoardList[i]}`, {
              headers: {
                "access-token": this.store.state.token,
              },
            })
            .then((res) => {
              this.myBoardMemberList[this.myRecruitBoardList[i]] = res.data;
            });
        }
      });
    // 토론 게시판 번호와 관련된 지원자 호출
    //applies에 저장

    console.log(this.store.state.applicant_recruit_array);
  },
  methods: {
    ...mapActions(["DEBATERECRUIT", "PUTAPPLY", "GETAPPLY"]),
    async debateRecruit() {
      await this.DEBATERECRUIT();
    },
    async getApply(board_no: number) {
      this.GETAPPLY(board_no);
    },
    putApply_no(num: number) {
      console.log(num);
      this.PUTAPPLY({
        applicant_no: num,
        accept: 0,
      });
    },
    putApply_go(num: number) {
      this.PUTAPPLY({
        applicant_no: num,
        accept: 1,
      });
    },
    boardtopic(board_no: number) {
      for (let i = 0; i < this.applicant_recruit_array.length; i++) {
        if (this.applicant_recruit_array[i].board_no == board_no) {
          return this.applicant_recruit_array[i].debate_topic;
        }
      }
    },
    // moveToRoom(no: number) {
    //   this.$router.push("/room/" + no);
    // },
    // myRecruit(memberinfo: { memberNo: any }, filter: any) {
    //   if (memberinfo.memberNo === filter) {
    //     return true;
    //   }
    //   return false;
    // },
    modalShow() {
      this.apply_modal = true;
      // 영범아 변수 틀렸어
      // console.log(this.applicant_recruit["board_no"]);
      // console.log(this.applicant_recruit["max_applicant"]);
    },
    modalHidden() {
      this.apply_modal = false;
    },
    openModal() {
      document.location.href = "#open-modal";
    },
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
* {
  font-family: "locus_sangsang";
}
.hidden {
  display: none;
}
.apply-check {
  width: 60px;
  height: 20px;
  margin-left: 86px;
  margin-top: 5px;
  font-size: 8px;
  background: #03e9f4;
  border-radius: 1px solid black;
  /* box-shadow: 0 0 1px #03e9f4, 0 0 5px #03e9f4, 0 0 10px #03e9f4,
    0 0 20px #03e9f4; */
}
.apply-modal {
  position: relative;
  width: 200px;
  border-radius: 1px solid black;
  margin-left: 5px;
  margin-top: 5px;
}
.apply-check:hover {
  cursor: pointer;
}
.apply-button {
  display: inline-block;
  width: 36px;
  height: 20px;
  font-size: 10px;
}
.userpage-content-room {
  position: relative;
  width: 150px;
  height: 150px;
  margin: 16px;
  left: 30px;
  display: inline-block;
  justify-content: center;
  align-items: center;
  background: #757f9a;
  box-shadow: 0 0 0.5px 1px rgba(200, 230, 255, 0.5),
    0 -0.5px 0.5px 1.5px rgba(200, 230, 255, 0.5) inset,
    0 0 0.5px 2.5px rgba(135, 131, 171, 0.9),
    0 0 0.5px 1.75px rgba(135, 131, 171, 0.9) inset,
    0 0 3px 3.25px rgba(123, 108, 196, 0.9),
    0 0 3px 2.75px rgba(123, 108, 196, 0.9) inset,
    0 2px 7.5px 4.5px rgba(0, 0, 0, 0.8),
    0 2px 6.25px 3px rgba(0, 0, 0, 0.7) inset;
}
.userpage-content-box {
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  border-radius: 10px;
  display: none;
  width: 400px;
  height: 400px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 20px;
  background: #757f9a;
  box-shadow: 0 0 1px 2px rgba(200, 230, 255, 0.5),
    0 -1px 1px 3px rgba(200, 230, 255, 0.5) inset,
    0 0 1px 5px rgba(135, 131, 171, 0.9),
    0 0 1px 3.5px rgba(135, 131, 171, 0.9) inset,
    0 0 6px 6.5px rgba(123, 108, 196, 0.9),
    0 0 6px 5.5px rgba(123, 108, 196, 0.9) inset,
    0 4px 15px 9px rgba(0, 0, 0, 0.8), 0 4px 12.5px 6px rgba(0, 0, 0, 0.7) inset;
}
.userpage-content-topic {
  position: absolute;
  margin-left: 10px;
  margin-right: 10px;
  /* font-family: "Inter"; */
  font-style: normal;
  /* font-weight: bold; */
  font-size: 14px;
  line-height: 44px;
  color: rgba(255, 255, 255);
  /* text-shadow: 0 0 5px rgba(255, 245, 0, 1), 0 0 28px rgba(255, 44, 0, 0.9),
    0 8px 38px black, 0 8px 10px black, 0 2px 12px black,
    0 0 50px rgba(255, 120, 0, 0.5), 0 0 24px rgba(255, 255, 255, 0.8);
  -webkit-text-stroke: 1px rgba(255, 160, 0, 0.45); */
}
.userpage-content-count {
  position: absolute;
  right: 12px;
  bottom: -6px;
  /* font-family: "Inter"; */
  font-style: italic;
  /* font-weight: 500; */
  font-size: 12px;
  line-height: 44px;
  text-align: center;
  color: #ffffff;
}
.userpage-content-room-button {
  position: absolute;
  top: 80%;
  left: 50%;
  transform: translate(-50%);
  /* font-family: "Inter"; */
  font-style: italic;
  /* font-weight: bolder; */
  font-size: 10px;
  text-align: center;
}
.userpage-content-room-button-start {
  padding: 7px 14px;
  color: #03e9f4;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #03e9f4;
  outline: 0;
  border-radius: 5px;
  font-weight: bold;
}
.userpage-content-room-button-start:hover {
  background: #03e9f4;
  color: #fff;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 100px #03e9f4;
}
.userpage-content-room-button-no {
  padding: 7px 14px;
  color: #ae2626;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #ae2626;
  outline: 0;
  border-radius: 5px;
  font-weight: bold;
}
.userpage-content-room-button-no:hover {
  background: #ae2626;
  color: #fff;
  box-shadow: 0 0 5px #ae2626, 0 0 25px #ae2626, 0 0 50px #ae2626,
    0 0 100px #ae2626;
}
.userpage-content-room-button-wait {
  padding: 7px 14px;
  color: #ffe53b;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #ffe53b;
  outline: 0;
  border-radius: 5px;
  font-weight: bold;
}
.userpage-content-room-button-wait:hover {
  background: #ffe53b;
  color: #fff;
  box-shadow: 0 0 5px #ffe53b, 0 0 25px #ffe53b, 0 0 50px #ffe53b,
    0 0 100px #ffe53b;
}
.userpage-content-room-button-apply {
  padding: 7px 14px;
  color: #03f46f;
  background: #070707;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.5s;
  letter-spacing: 4px;
  border: 1px solid #03f46f;
  outline: 0;
  border-radius: 15px;
  font-weight: bold;
}
.userpage-content-room-button-apply:hover {
  background: #03f46f;
  color: #fff;
  box-shadow: 0 0 5px #03f46f, 0 0 25px #03f46f, 0 0 50px #03f46f,
    0 0 100px #03f46f;
}
#create {
  margin-bottom: 500px;
}
.modal-content {
  background: rgb(46, 37, 37);
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
  font-size: 32px;
  margin-left: 50%;
  transform: translate(-50%);
}
/* .btn-close {
  background: white;
} */
/* .modal-backdrop {
  visibility: hidden;
} */
/* .modal-backdrop {
  z-index: -10 !important;
} */
</style>
