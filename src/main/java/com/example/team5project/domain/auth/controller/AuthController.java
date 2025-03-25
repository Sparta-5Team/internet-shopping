package com.example.team5project.domain.auth.controller;

import com.example.team5project.domain.auth.dto.request.SigninRequest;
import com.example.team5project.domain.auth.dto.request.SignupRequest;
import com.example.team5project.domain.auth.dto.request.WithdrawRequest;
import com.example.team5project.domain.auth.dto.response.SigninResponse;
import com.example.team5project.domain.auth.dto.response.SignupResponse;
import com.example.team5project.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authService.signup(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<SigninResponse> login(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authService.login(signinRequest));
    }

    @DeleteMapping("/withdraw")
    public void withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        authService.withdraw(withdrawRequest);
    }
}
