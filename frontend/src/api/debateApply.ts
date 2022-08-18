import { debateApply } from "./index";

function getApply(board_no: number) {
  return debateApply.get("/" + board_no);
}
function postApply() {
  return debateApply.post("/");
}
function putApply(num: number) {
  return debateApply.put("/" + num);
}
function deleteApply(num: number) {
  return debateApply.delete("/" + num);
}
function getDebateApply() {
  return debateApply.get("/applying");
}
function getDebateRecruit() {
  return debateApply.get("/recruiting");
}
export {
  getApply,
  postApply,
  putApply,
  deleteApply,
  getDebateApply,
  getDebateRecruit,
};
