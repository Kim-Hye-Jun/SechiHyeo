export interface RoomThumbnailInfo {
  thumbnailSrc: string;
  roomId: string;
  roomTitle: string;
  maxNumOfPeople: number;
  currentNumOfPeople: number;
  sideOrderList: Array<SideOrderInfo>;
}

export interface SideOrderInfo {
  sideOrder: string;
  empty: boolean;
}

export interface RoomCreateRequestInfo {
  roomTitle: string;
  debateTopic: string;
  headCount: number;
  roomType: string;
  debateType: string;
  password?: string;
  sideA: string;
  sideB: string;
}

export interface RoomCreateResponseInfo {
  roomId: string;
}

export interface RoomJoinRequestInfo {
  roomId: string;
  side: string;
  order: number;
}

export interface RoomJoinResponseInfo {
  roomId: string;
  tokenCamera: string;
  tokenScreen: string;
  roomHost: boolean;
  roomTitle: string;
  sideA: string;
  sideB: string;
  currentNumOfPeople: number;
  maxNumOfPeople: number;
  userSideOrder: string;
  emptySideOrderList: Array<string>;
}

export interface RoomUpdateUserSideOrderRequestInfo {
  roomId: string;
  loginId: string;
  preSideOrder: string;
  newSideOrder: string;
}
