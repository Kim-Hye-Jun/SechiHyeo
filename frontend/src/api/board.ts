import { board } from "./index";

function getBoards() {
  return board.get("/");
}
function getBoard(num: number) {
  return board.get("/" + num);
}
function postBoard() {
  return board.post("/");
}
function putBoard(num: number) {
  return board.put("/" + num);
}
function deleteBoard(num: number) {
  return board.delete("/" + num);
}
export { getBoards, getBoard, postBoard, putBoard, deleteBoard };
