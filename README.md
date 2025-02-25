# Work Language Corrector
***

#### 기능
1. 단어 교정
 - [ ] 사용자가 질의한 단어가 올바른지 뜻을 확인
 - [ ] 올바르다면 단어의 뜻과 함께 사용해도 좋다고 알려줌
 - [ ] 올바르지 않다면, 올바른 단어를 찾아 반환
2. 문장 교정
 - [ ] 사용자가 입력한 문장에서 교정이 필요한 단어가 있다면 교정해서 반환
3. 오늘의 단어
 - [ ] 랜덤한 오늘의 단어와 그 뜻을 보여줌


#### 기술
Back-end: SpringBoot, Spring WebFlux, JPA, H2(임시) </br>
Front-end: React, TypeScript (예정)


##### 
Spring WebFlux 환경에서의 h2-console은 일반적인 SpringBoot 환경에서와 사용법이 다르다.<br/>
따라서, `H2ConsoleConfig` 를 통해 H2 WebServer를 사용하고, Console은 `localhost:8078`을 통해 접근한다.