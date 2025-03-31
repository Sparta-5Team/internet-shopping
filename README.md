# 인터넷 쇼핑몰 조회 프로젝트 (5조)

## 프로젝트 정보
| 구분     | 내용                                                           |
|--------|--------------------------------------------------------------|
| 기간     | 2025.02.28 ~ 2025.03.07 (7일)                                 |
| 팀원     | 최다원(팀장), 박수환, 김원준, 오명제                               |
| 설명     | 서울시의 인터넷 쇼핑몰 현황을 간편하게 조회할 수 있는 프로젝트 |
| 사용 기술  | Java, Spring MVC, JPA, Spring Data JPA, MySQL                |
| JDK    | Amazon Corretto 17.0.14                                      |
| Spring | Boot 3.4.2, Core 6.2.2                                       |
  
<br/><br/>

## 역할 분담

| 팀원  | 내용                             |
|-----|--------------------------------|
| 최다원 | 회원 구현 및 Spring Security 구현, OpenAPI를 활용해 DB 입력, 인덱스를 활용한 성능 최적화 |
| 박수환 | 회원 구현 및 QueryDSL을 사용한 커서 기반 페이지네이션 및 필터, JWT를 사용한 인증/인가 구현             |
| 오명제 | Pageable 기반 업체 리스트 조회 |
| 김원준 | 인텔리제이를 활용하여 CSV 파일을 데이터베이스에 입력, CSV를 DB에 입력하는 코드 만들기| 


<br/><br/>

## 와이어 프레임
![업체조회 - 필터x PNG](https://github.com/user-attachments/assets/9b017188-5c4f-4e80-8c2a-f34cd1fa1d7b)
![업체조회 - 업체상태필터 PNG](https://github.com/user-attachments/assets/38580b77-5f1a-4e81-8e2f-519ebed93d70)
![업체조회 - 평가점수필터 PNG](https://github.com/user-attachments/assets/5ee27fd6-3fb3-4a47-bcee-db681dfc24bb)
![업체조회 - 평가점수, 업체상태 필터 PNG](https://github.com/user-attachments/assets/e60e6f76-3740-49fd-921f-ab10136a9cb8)


## ERD
![____________________erd_360](https://github.com/user-attachments/assets/eea325e9-693b-47ae-b24e-e4a35458e519)
![____________________erd_720](https://github.com/user-attachments/assets/814ad86e-ba65-4318-bb79-70e501155460)

<br/><br/>

## API 명세서
<br/>

<details>
  <summary><b>회원가입</b></summary>
  <br/>
  <div>
    ![image](https://github.com/user-attachments/assets/5b054935-a32e-49df-8cf5-0f133aa66f0e)
  </div>
</details>

<details>
  <summary><b>필터 검색</b></summary>
  <br/>
  <div>
      ![image](https://github.com/user-attachments/assets/b12663ee-d31c-40c0-92d3-f5afa2be876a)
  </div>
</details>


