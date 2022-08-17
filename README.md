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
***기획 (와이어프레임, 기능명세서, ERD, API docs 등은 팀 전원 작성)***
```
<역할>
* 김영범: 팀장, FE, 마이페이지, 게시판, 협업 툴 관리
* 김혜준: FE, WebRTC(Openvidu), 방기능, 자료공유 기능
* 양희경: BE, WebRTC(Openvidu), 방기능, 자료공유 기능
* 이종인: BE, 게시판, 인프라 담당, ec2 서버 관리 및 배포
* 정다은: BE, 회원기능, JWT, Spring Boot 프로젝트 세팅
* 홍성영: FE, 회원기능, JWT, 토론규칙 구현, 타이머 기능
```

<!-- 자유 양식 -->

## 프로젝트 상세 설명

// 개발 환경, 기술 스택, 시스템 구성도, ERD, 기능 상세 설명 등
### 1. 아키텍쳐 구조도
<!-- 링크 삽입 예정 & 포트번호 수정 예정 -->
| PORT | 이름                                                  |
| ---- | ----------------------------------------------------- |
| 443  | HTTPS                                                 |
| 80   | HTTP - HTTPS로 리다이렉트 (프론트 페이지로 리다이렉트)  |
| 3306 | MySQL                                                 |
| 8080 | Spring boot Docker Container                          |
| 8443 | Openvidu                                              |

### 2. 와이어프레임
![Image_Pasted_at_2022-8-17_23-12](/uploads/384559dcf91927696573e73e38663d6d/Image_Pasted_at_2022-8-17_23-12.png)

### 3. ERD
<!-- 링크 삽입 예정 -->
![Image_Pasted_at_2022-8-17_23-07](/uploads/2f743881d275ddb736d5449f6edde084/Image_Pasted_at_2022-8-17_23-07.png)

### 4. API 명세서
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
| 모집 게시글 작성 | POST | /api/debate-board | 모집 게시글을 작성한다. |
| 모집 게시글 조회 | GET | /api/debate-board/{board_no} | 모집 게시글을 board_no에 맞춰서 조회해준다. |
| 모집 게시글 수정 | PUT | /api/debate-board/{board_no} | 모집 게시글을 수정한다. |
| 모집 게시글 삭제 | DELETE | /api/debate-board/{board_no} | 모집 게시글을 삭제해준다. |

<br/>
<br/>

**댓글**
|기능|Method|URL|Response|
|:--:|:--:|:--:|:--:|
| 댓글 수정 | PUT | /api/debate-reply |
| 댓글 등록 | POST | /api/debate-reply/{board_no} | 댓글을 parent_no,depth에 맞춰서 context를 등록한다. |
| 댓글 삭제 | DELETE | /api/debate-reply/{reply_no} | 댓글을 삭제한다. |

<br/>
<br/>

**회원 관리**
| 기능 | Method | URL | Response |
|:--:|:--:|:--:|:--:|
| 회원 탈퇴 | DELETE | /api/member | 현재 생성되어 있는 방 전체 목록을 반환한다. |
| 비밀번호 변경 자격 확인 | POST | /api/member/auth-pw | 헤더에 보내진 jwt 토큰과 비밀번호로, 멤버 정보를 대조하여 본인여부를 확인합니다. |
| 회원 정보 수정 | POST | /api/member/introduce | 헤더에 보내진 jwt 토큰과 수정정보를 이용해, 멤버의 정보를 수정한다. |
| 로그인 | POST | /sessions/{room_id}/thumbnail | 입력된 아이디와 비밀번호를 확인하여 로그인을 진행합니다. |
| 비밀번호 변경 | PUT | /api/member/password | 헤더에 보내진 jwt 토큰과 수정정보를 이용해, 멤버의 정보를 수정합니다. 수정이 완료되면 로그아웃 합니다. |
| 멤버 정보 조회 | GET | /api/member/profile | 헤더에 보내진 jwt 토큰을 복호화하여, 토큰에 담긴 멤버 정보를 전송합니다. |
| 프로필 이미지 수정(업로드) | PUT | ​/api​/member​/profile-image | 헤더에 보내진 jwt 토큰을 복호화하여, 토큰에 담긴 멤버 정보를 전송합니다. |

<br/>
<br/>

**멤버 등록**
|기능|Method|URL|Response|
|:--:|:--:|:--:|:--:|
| 이메일 중복 체크 | GET | /api/register/email/{email} | 현재 회원가입하려고 하는 이메일({email})을 다른 사용자가 이용중인지 확인합니다 |
| 닉네임 중복 체크 | GET | /api/register/nickname/{nickname}| 현재 회원가입하려고 하는 닉네임({nickname})를 다른 사용자가 이용중인지 확인합니다 |
| 휴대폰 번호 중복 체크 | GET | /api/register/phone-number/{phone_number} | 현재 회원가입하려고 하는 번호({phone_number})를 다른 사용자가 이용중인지 확인합니다 |
| 회원가입 | POST | /api/register/signup | 회원가입을 통해 멤버 정보를 등록합니다 |
| 아이디 중복 체크 | GET | /api/register/user-id/{login_id} | 현재 회원가입하려고 하는 id({login_id})를 다른 사용자가 이용중인지 확인합니다 |

<br/>
<br/>

**방**
| 기능 | Method | URL | Response |
|:--:|:--:|:--:|:--:|
| 전체 방 조회 | GET | /sessions/{page_info} | 현재 생성되어 있는 방 전체 목록을 반환한다. |
| 키워드로 방 검색 | GET | /sessions/{page_info}/keyword | 키워드로 방을 검색한다. |
| 방 생성 | POST | /sessions/ | 토론 방 객체와 오픈비두 세션을 생성한다. |
| 방 썸네일 파일 업로드 | POST | /sessions/{room_id}/thumbnail | 토론방의 썸네일 파일을 업로드한다. |
| 자료 공유 관련 이미지 파일 주소 반환 | POST | /sessions/uploadProof | 토론 자료 공유를 위해 사용자가 자료 파일을 업로드한다. |
| 방 접속 | POST | /sessions/connection_select | 사용자가 원하는 진영과 순서를 선택해 토론방에 접속한다. |
| 방 퇴장 | GET | /sessions/{room_id}/disconnect | 사용자가 토론방에서 퇴장한다. |
| 토론 진영, 순서 저장 및 토론 규칙 반환 | POST | /sessions/settings | 방장이 새로 설정한 토론 진영 및 순서를 저장하고 그에 따른 토론 규칙을 반환한다. |
| 현재 비어있는 진영, 순서 반환 | GET | /sessions/{rood_id}/empty | 토론 방 내 현재 비어있는 진영 및 순서를 반환한다. |
| 접속자 진영, 순서 배치 갱신 | PUT | /sessions/sideOrder/ | 새로 설정된 접속자들의 진영, 순서대로 화면을 배치하는 시그널을 보낸다. |
| 토론 종료에 따른 사용자 정보 갱신 | PUT | /sessions/{room_id}/end | 토론 종료 후 토론방에 접속해 있는 사용자들의 경험치와 토론 전적을 갱신한다. |
| 현재 생성된 방 개수 | GET | /sessions/pageNum | 현재 생성된 방 개수를 반환한다. |
| 현재 생성된 방 정보 | GET | /sessions/roomInfo | 현재 생성된 모든 방에 대한 정보를 반환한다. |

## Git Convention
### 1. Branch
```
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
```

### 2. Commit message

> 1.  소스코드 작성 및 Git 작업을 시작하기 전에 JIRA 이슈 생성하기
> 2.  서로 공유하는 commit 그래프는 함부로 변경하지 않기
> 3.  꼭 코드리뷰 받고 merge하기
> 4.  자신의 pull request는 스스로 merge하기

### 3. commit message 형식

```
<type> : <subject>

<body>      // 생략가능

<footer>    // 생략가능
```

### 4. type: 커밋 유형의 종류

```
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 수정
style : 코드 의미에 영향을 주지 않는 변경사항 (포맷팅, 세미콜론 누락, 공백 등)
refactor : 성능 개선 or 리팩토링
test : 테스트 추가, 테스트 코드 리팩토링, 개발 코드는 변경 X
chore : 그 외 자잘한 작업, 빌드 업무, 패키지 매니저(ex config) 설정관리, 패키지 업데이트 등등
```

### 5. subject: 제목 컨벤션
```
- 너무 길지 않게 50자를 넘기지 않는다.
- 마침표를 붙이지 않는다.
- 개조식 구문을 영어로 작성 (ex. feat : Adding Log Output Features)
```

### 6. body: 본문 컨벤션

- 생략가능
- 제목과 본문은 한 줄 띄워 구분한다.
- 부연설명이나, 커밋의 이유 작성할 경우 사용
- 어떻게 보다는 `무엇 / 왜` 대해서 작성

### 7. footer: 꼬리말 컨벤션
```
- 생략
```

### 8. commit message 예시

```
feat : Implementation of login functionality

로그인페이지 관련 마크업을 진행했습니다
로그인 유효성 검사에 관한 코드를 작성했습니다

#S04P12A202-26
```

### 9. git-flow 전략
```
- master : 기준이 되는 브랜치로 배포할 수 있는 브랜치 입니다.
- develop : 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 pull하고 개발 후 합(Merge)칩니다.
- feature : 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 develop 브랜치에 합칩니다.
- release : 배포를 위해 master 브랜치로 보내기 전에 먼저 QA(품질검사)를 하기위한 브랜치 입니다.
- hotfix : master 브랜치로 배포를 했는데 버그가 생겼을 떄 긴급 수정하는 브랜치 입니다.
```

### 10. git branch 이름 정책

- feat/{frontEnd or backEnd}/{ 기능 설명 (영어)} → 기능 수행 (front/ back)
- merge는 자유롭게

### 11. Merge Request

```
제목
merge : <From Branch Name> -> <To Branch Name>

내용
# 이 PR 이 다루는 내용

# 새로 사용한 기술 / 공유해야 할 내용    // 생략가능

# Screenshot     // 생략가능

# 질문     // 생략가능
```

예시

```
제목
merge : develop -> main

내용
#수정사항 ( 이 PR 이 다루는 내용 )
1. 회원가입 시 입력 폼 피드백 추가
2. 프로그레스바 css 수정
3. 아이콘 클릭되지 않는 문제 해결


# 아이콘이 클릭되지 않는 문제 ( 새로 사용한 기술 / 공유해야 할 내용 )
### 현상
- Header.vue의 Icon (왼쪽, 오른쪽) 클릭되지 않았습니다.

### 원인
- Header 컴포넌트 내 H1 태그의 width가 100%로 Icon 위쪽으로 가로 가득하게 위치했기 때문

### 해결 방법
- 클릭 이벤트가 발생되어야 할 아이콘에 z-index를 10으로 설정하여
  아이콘이 H1 태그보다 z축으로 위에 위치하도록 수정
```
