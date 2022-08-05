# 프로젝트 세팅 내용

## application.properties
- 서버 포트번호 8080
- context path: /api/
- db 설정
  - 스키마 이름: ssafy_web_db
  - ID: root
  - PW: seoulA508!
  - **본인 로컬 설정에 맞게 바꾸기**


## build.gradle
- 여러 dependency 추가됨
  - jwt
  - mysql
  - javassist
  - springfox(swagger)
  - commons-fileupload
  - validation(유효성 검사용)
  - **그 외 필요한 의존성 있다면 추가**


## main.java.com.ssafy.backend.config.WebConfig
- cors 허용
- swagger ui 리소스
- jwt interceptor 예외 리소스 정의
  - *필요시, 예외 경로 추가하면 됩니다*
- **코드가 많지 않아서 WebConfig 안에 한번에 넣어놓음. 마음에 안들면 나눠도 상관없어요...**


### swagger-ui: "http://localhost:8080/api/swagger-ui/index.html" 에서 테스트