## 🔥Hot-Dealicious🚘

### 주변 매장을 조회하고 주문 및 결제할 수 있는 배달 서비스 (V1)

----------

### 🌱 프로젝트 핵심 기능

1) 회원가입/로그인

- Redis Session을 활용한 로그인 관리

2) 높은 트래픽 견디기

- 로드벨런싱

3) 실시간 라이더 위치 정보 제공

- 네트워크 WebSocket 관리

4) 주문/결제 시스템

- 동시성 이슈(재고 처리, 동시 배차 등) 처리
- 주문과 결제의 데이터 정합성 (트랜젝션) 처리
- 외부 API 사용 (토스 가상결제 시스템 도입)

----------

### 🌱 필요 기술 Stack

- for Application : Java 11, Spring Boot(2.7.12), MyBatis
- for DataBase : MySQL, Redis
- for CI/CD : Jenkins, Docker, Git
- for Server : Nginx, HA Proxy, AWS EC2

----------

### 🌱 프로젝트 목표

- 대용량 트래픽을 견딜 수 있는 아키텍처 설계 및 코드 작성
- 효율적인 트랜젝션 처리 및 동시성 이슈 처리
- 네트워크 개념 실전 활용(WebSocket 등)
- 각종 데이터를 보관하기 적절한 Database 선정 및 활용
- 결제 도메인 및 PG사와의 결제 Flow(토스 한정)
- CI/CD Pipline 구축
- 모니터링 시스템 구축 (시스템 모니터링, 로그 모니터링)

----------

### 🌱 프로젝트 ERD

<img width="2125" alt="Screenshot 2023-06-11 at 3 31 58 PM" src="https://wkblog-images.s3.ap-northeast-2.amazonaws.com/hot-dealicious/DB-modeling.png">

----------

### 🌱 프로젝트 예상 설계

![C5009FDA-CF48-4616-B960-EE3EB9DF4189](https://wkblog-images.s3.ap-northeast-2.amazonaws.com/hot-dealicious/Project+Architecture.jpeg)

----------

### 🌱 Git Branch 전략

- master : 제품으로 출시될 수 있는 브랜치 (서버 배포용 브랜치) <br />
- develop : 다음 출시 버전을 개발하는 브랜치 (feature 분기점 브랜치) <br />
- feature : 기능을 개발하는 브랜치 <br />
- refactor : 기능을 리팩토링하는 브랜치
- doc : 문서 업데이트 브랜치

----------

### 🌱 코드 컨벤션

- 원활한 Team Project를 위해 [네이버 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/)을 적용합니다.

###### 참고문헌 : https://naver.github.io/hackday-conventions-java/

----------

