import { member } from "./index";

function getMemberInfo() {
  //   console.log(member);
  return member.get("member/profile");
}

export { getMemberInfo };
