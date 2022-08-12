# 웹/모바일(웹 기술) 스켈레톤 프로젝트

<!-- 필수 항목 -->

## 카테고리

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :white_check_mark: Vue.js |
| :white_check_mark: Mobile Web | :black_square_button: Big Data | :black_square_button: TypeScript | :black_square_button: React |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :black_square_button: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :white_check_mark: Python | :white_check_mark: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |

<!-- 필수 항목 -->

## 프로젝트 소개

* 프로젝트명: 그룹 비디오 컨퍼런스 서비스
* 서비스 특징: 웹/모바일(웹 기술) 프로젝트를 위한 스켈레톤 프로젝트
* 주요 기능
  - 회원 관리
  - 화상 미팅룸
  - 그룹 채팅
* 주요 기술
  - WebRTC
  - WebSocket
  - JWT Authentication
  - REST API
* 참조 리소스
  * Vuetify: 디자인 전반 적용
  * Vue Argon Design System: 디자인 전반 적용
  * Vue Black Dashboard Pro(유료): 캘린더 컴포넌트 사용
  * AR Core: 구글에서 제공하는 AR 지원 라이브러리. 이미지 인식 및 오버레이 영상에 활용
  * Color Thief: 이미지 색상 추출 라이브러리. 커버 사진 색상 추출 및 배경 변경에 활용
  * Animation.css: CSS 애니메이션 지원 라이브러리. 메인 페이지 진입 애니메이션에 활용
* 배포 환경
  - URL: // 웹 서비스, 랜딩 페이지, 프로젝트 소개 등의 배포 URL 기입
  - 테스트 계정: // 로그인이 필요한 경우, 사용 가능한 테스트 계정(ID/PW) 기입

<!-- 자유 양식 -->

## 팀 소개
* 김ㅇㅇ: 팀장, 프론트엔드 개발
* 이ㅇㅇ: 부팀장, 기획 및 와이어프레임 작성, 프론트엔드 개발
* 박ㅇㅇ: 백엔드 개발 및 QA 담당
* 홍ㅇㅇ: 백엔드 개발, Swagger API 문서 관리
* 전ㅇㅇ: 코드 리뷰 및 인프라 담당, CI/CD, HTTPS, Docker 구성

<!-- 자유 양식 -->

## 프로젝트 상세 설명

// 개발 환경, 기술 스택, 시스템 구성도, ERD, 기능 상세 설명 등

## Git Convention
### 1. Branch

main
develop
feature/front/{기능명}/{이니셜}
feature/back/{기능명}/{이니셜}

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
