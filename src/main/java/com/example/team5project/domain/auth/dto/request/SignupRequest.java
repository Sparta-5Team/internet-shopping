package com.example.team5project.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequest {

    @NotBlank
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 8, max = 24, message = "비밀번호는 8자 이상 24자 이하이어야 합니다.")
    private String password;
}
