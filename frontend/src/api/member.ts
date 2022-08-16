import { member } from "./index";

// const form = new FormData();
function getMemberInfo() {
  return member.get("/profile");
}
function getExpInfo() {
  return member.get("/exp");
}
function getRecord() {
  return member.get("/record");
}
// function putProfileImage() {
//   return member.put("/profile-image", form, {
//     headers: {
//       "Content-Type": "multipart/form-data",
//     },
//   });
// }
function putProfileImage() {
  return member.put("/profile-image");
}
function putIntroduce() {
  return member.put("/introduce");
}
function postAuthPw() {
  return member.put("/auth-pw");
}
function putPassword() {
  return member.put("/password");
}
function deleteMember() {
  return member.delete("/");
}
export {
  getMemberInfo,
  getExpInfo,
  getRecord,
  putProfileImage,
  putIntroduce,
  postAuthPw,
  putPassword,
  deleteMember,
};
