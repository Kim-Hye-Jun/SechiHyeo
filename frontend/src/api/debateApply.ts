import { debateApply } from "./index";

function postApply() {
  return debateApply.get("/");
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
export { postApply, putApply, deleteApply, getDebateApply, getDebateRecruit };
