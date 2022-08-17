# 세 치 혀 프로젝트(웹 기술, 화상회의)

<!-- 필수 항목 -->

## 카테고리

| Application | Language | Framework |
| ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: JavaScript | :white_check_mark: Vue.js |
| :black_square_button: Mobile Web | :white_check_mark: TypeScript | :black_square_button: React |
| :black_square_button: Responsive Web | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: C# | :white_check_mark: Node.js |
| :black_square_button: iOS App | :black_square_button: Python | :black_square_button: Flask/Django |
| :black_square_button: Desktop App | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | :black_square_button: Kotlin | |

<!-- 필수 항목 -->

## 프로젝트 소개

* 프로젝트명: 세 치 혀
* 서비스 특징: 다양한 연령층을 대상으로 토론 대회를 준비하거나 토론을 즐기고 싶어하는 사람들을 위해 토론 규칙 및 타이머 기능을 제공하는 화상 회의 플랫폼
* 주요 기능
  -	토론 모집
    - 미리 토론에 참여할 인원들 모집 가능
    - 방장 참여 수락 후, 참여 가능 상태로 변경
  -	토론방 생성
    -	주제 / 진영 / 인원수 설정 등 가능
    -	인원수 설정 시, 토론 규칙 자동 적용
  - 토론 진행(방 내부 기능)
    - 설정된 규칙에 따라 타이머 작동하면서 자동 진행
    -	발표자는 자료 공유 가능
    -	진영에 따라 화면 좌우로 나뉘어져서 나타남
    - 토론 완료 시, 경험치 +10 누적 적립
  - 회원기능
    - 회원가입, 로그인, 마이페이지 제공
    - jwt 토큰 발급을 통한 인증
  
* 주요 기술
  - Vue.js 3 (3.2.37)
  - Visual Studio Code (1.69)
  - TypeScript (5.4.0)
  - Openvidu(2.22.0)
  - Spring Boot (2.7.2)
  - Spring Data Jpa (2.7.2)
  - JWT (0.9.1)
  - java 11 (Zulu 11.0.15 -win64)
  - intellij (221.5921.22)
  - MySQL (8.0.28 Workbench) 
  - docker (20.10.17)
  - nginx (1.18.0 Ubuntu)
  - REST API

* 협업툴
  - GitLab
  - Jira

* 참조 리소스
  * Vue Atomic Design System: 디자인 전반 적용
  
* 배포 환경
  - URL: https://i7a508.p.ssafy.io
    // 웹 서비스, 랜딩 페이지, 프로젝트 소개 등의 배포 URL
  - 테스트 계정: qwer1324 / 12345678
    // 로그인이 필요한 경우, 사용 가능한 테스트 계정(ID/PW)

<!-- 자유 양식 -->

## 팀 소개
### 기획 (와이어프레임, 기능명세서, ERD, API docs 등은 팀 전원 작성)
* 김영범: 팀장, FE, 마이페이지, 게시판, 협업 툴 관리
* 김혜준: FE, WebRTC(Openvidu), 방기능, 자료공유 기능
* 양희경: BE, WebRTC(Openvidu), 방기능, 자료공유 기능
* 이종인: BE, 게시판, 인프라 담당, ec2 서버 관리 및 배포
* 정다은: BE, 회원기능, JWT, Spring Boot 프로젝트 세팅
* 홍성영: FE, 회원기능, JWT, 토론규칙 구현, 타이머 기능
----------------------------------------------------
* member 관련:
  - 
* room 관련:
* board 관련: 
* openvidu 관련:
---------------------------------------------
* 각자적기!


<!-- 자유 양식 -->

## 프로젝트 상세 설명

// 개발 환경, 기술 스택, 시스템 구성도, ERD, 기능 상세 설명 등
### 1. 아키텍쳐 구조도
<!-- 링크 삽입 예정 & 포트번호 수정 예정 -->
| PORT | 이름                                                  |
| ---- | ----------------------------------------------------- |
| 443  | HTTPS                                                 |
| 80   | HTTP - HTTPS로 리다이렉트 (프론트 페이지로 리다이렉트)  |
| 3302 | MySQL                                                 |
| 3000 | Vue, NginX Docker Container                           |
| 8080 | Spring boot Docker Container                          |
| 8443 | Openvidu                                              |

### 2. ERD
<!-- 링크 삽입 예정 -->

### 3. API 명세서
## 📃API 설계
**토론 신청 정보**
|기능|Method|URL|Response|
|:--:|:--:|:--:|:--:|
| 토론 참여 신청 | POST | ​/api​/debate-apply | 토론 참여를 신청한다. | 
| 토론 참여 수정 | PUT | /api/debate-apply/{apply_no} |토론 참여를 수정한다. |
| 토론 참여 삭제 | DELETE | /api/debate-apply/{apply_no} | 토론 참여를 삭제한다. |
| 토론 모집 조회 | GET | /api/debate-apply/applying | 내가 신청한 applicant_state 조회 |
| 토론 모집 조회 | GET | /api/debate-apply/recruiting | 내가 모집한 applicant_state 조회 |

<br/>
<br/>

**토론 모집 게시판**
|기능|Method|URL|Response|
|:--:|:--:|:--:|:--:|
| 모집 게시글 전체 조회 | GET | /api/debate-board | 모집 게시글을 전체 조회해준다. |
|비디오 선택|GET|/video/detail/{id}|id에 해당하는 비디오정보 및 평점, 좋아요 횟수를 보여준다.|
|시청 기록|GET|/video/watched|회원의 시청기록을 보여준다.|
|찜한 비디오|GET|/video/liked|찜한 동영상을 보여준다.|
|좋아요 클릭|POST|/video/likes|좋아요 버튼 클릭시 찜 테이블에 저장된다.|
|좋아요 취소|DELETE|/video/likes/{id}|좋아요 취소시 찜 테이블에서 삭제된다.|
|부위 종류 반환|GET|/video/part|DB에 있는 비디오의 부위 정보를 조회횐다.|
|추천 영상|GET|/video/recommended|회원별 추천 영상을 조회한다.|


<br/>
<br/>

**리뷰**
|기능|Method|URL|Response|
|:--:|:--:|:--:|:--:|
|리뷰 등록|POST|/review/write|리뷰를 작성한다.|
|리뷰 수정|PUT|/review/update/{id}|리뷰를 수정한다.|
|리뷰 삭제|DELETE|/review/delete/{id}|리뷰를 삭제한다.|
|비디오 전체 리뷰 조회|GET|/review/all/{id}|선택한 비디오의 전체 리뷰를 조회한다.|
|비디오 회원 리뷰 조회|GET|/review/all/{vid}/{uid}|선택한 비디오에서의 해당 회원이 작성한 리뷰를 조회한다.|


<br/>
<br/>



## Git Convention
### 1. Branch
- main
- develop
- feat/front/board_mypage_end/kyb
- feat/front/board_mypage_End/kyb
- feat/front/board_room_apply/kyb
- feat/back/room/khj
- feat/front/room-view/hj
- feat/front/room/hj
- feat/fe/screen-share/hj
- feat/back/room/yhk
- feat/back/roomSignal/yhk
- feat/BE/BoardResBoardAdd/LJI
- Feat/BE/FixAndRefactor/LJI
- feafure/BE/transtional/LJI
- feature/BE/DB_refactoring/LJI
- feat/back/boardAndReplyTest/lji
- feature/BE/BoardAndReplyEnd/LJI
- feat/back/boardAndReply/lji
- feat/back/member/jde
- feat/font/SignUp-design/hsy
- feat/front/Main-design/hsy
- feat/front/timer-rule/hsy
- feat/front/chagne-Room-entrance/hsy
- feat/back/readme/jde

### 2. Commit message

**type** : {영어}
**body** : 설명
**footer** : 생략 가능

<aside>
💡 **Type 종류**
**feat** : 새로운 기능 추가
**fix** : 버그 수정
**docs** : 문서 수정
**style** : 코드 의미에 영향을 주지 않는 변경사항 (포맷팅, 세미콜론 누락, 공백 등)
**refactor** : 성능 개선 or 리팩토링
**test** : 테스트 추가, 테스트 코드 리팩토링, 개발 코드는 변경 X
**chore** : 그 외 자잘한 작업, 빌드 업무, 패키지 매니저(ex config) 설정관리, 패키지 업데이트 등등
***** 추후 합의에 따라 추가 가능!
</aside>

### 3. Merge Message

Merge {하위 Branch 이름} -> {상위 Branch 이름}
