export interface RoomThumbnailInfo {
  thumbnailSrc: string;
  roomId: string;
  roomTitle: string;
  maxNumOfPeople: number;
  currentNumOfPeople: number;
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

export interface RoomJoinResponseInfo {
  token: string;
  roomTitle: string;
  sideA: string;
  sideB: string;
  maxNumOfPeople: number;
  userSideOrder: string;
  emptySideOrderList: Array<string>;
}
