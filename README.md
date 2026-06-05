# webprogramming2

JSP, CSS, JDBC를 사용한 여론 조사 예제 프로젝트입니다.

## 구성

- `HomeController`: 시작 화면 이동
- `VoteController`: 투표 입력 및 저장
- `ResultController`: 결과 조회
- `SurveyDAO`: JDBC로 데이터 조회 및 수정

## 과제 요구사항 반영

- MVC 패턴 적용
- 한 사용자가 여러 번 투표 가능
- 결과는 투표율 내림차순 정렬
- 초기 데이터는 각 과일 25표로 동일하게 설정

## 실행 전 준비

1. MySQL에 [`db/schema.sql`](./db/schema.sql)을 실행합니다.
2. [`src/main/java/com/example/survey/util/DBUtil.java`](./src/main/java/com/example/survey/util/DBUtil.java)의 DB 접속 정보를 환경에 맞게 수정합니다.
3. 프로젝트에 MySQL JDBC 드라이버와 JSTL 라이브러리를 추가합니다.
