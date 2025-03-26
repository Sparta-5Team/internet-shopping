package com.example.team5project.domain.auth.dto.request;

// AuthService 클래스의 getUser() 메서드 다형성을 위해서 만듦.
// 공통 로직 처리를 위함.
public interface UserRequest {

    String getEmail();
    String getPassword();
}
