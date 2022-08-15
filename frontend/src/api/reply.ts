import { reply } from "./index";

function postReply() {
  return reply.put("/");
}
function putReply(num: number) {
  return reply.put("/" + num);
}
function deleteReply(num: number) {
  return reply.delete("/" + num);
}

export { postReply, putReply, deleteReply };
