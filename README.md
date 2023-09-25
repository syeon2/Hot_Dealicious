## 🔥Hot-Dealicious🚘

### 위치 정보 제공 토이 프로젝트

배달 플랫폼에서 볼 수 있는 라이더 실시간 위치 조회 서비스를 모티브하였습니다. <br />
WebSocket & STOMP 통신 기법을 사용하여 실시간으로 라이더의 위치를 서버에 저장하고, 소비자 클라이언트에서 Polling 방식으로 라이더의 위치를 조회할 수 있도록 설계하였습니다.

WebSocket 서버 또한 대규모 트래픽이 발생된다는 전제로 WebSocket 서버의 Scale out 방안을 고민하였습니다.

----------

### 🌱 사용 기술 Stack

for Application : Java 11, Spring Boot(2.7.12), MyBatis <br />
for Database : MySQL, Redis <br />
for Network : WebSocket & STOMP <br />

----------

### 🌱 Architecture

<img width="850" alt="Screenshot 2023-09-17 at 5 01 43 PM" src="https://github.com/syeon2/Hot_Dealicious/assets/71717303/22b7b100-6f72-4d37-bd48-6b73fd077160">

----------

### 🌱 프로젝트 주요 관심사
- 코드 컨벤션 : Naver CheckStyle 적용
- Redis 사용 : 라이더의 위치를 지속적으로 저장해야할 필요성을 In-Memory 기반의 Redis를 사용하여 I/O의 오버헤드를 최소화

-----------

### 🌱 프로젝트 핵심 기능

- Redis Session을 활용한 로그인 인가 처리
- WebSocket & STOMP를 사용하여 실시간 네트워크 통신 처리
- Redis로 Distrubution Lock 개념을 사용하여 라이더 동시 배정 이슈 해결
