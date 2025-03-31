package com.example.team5project.domain.auth.service;

import com.example.team5project.domain.auth.dto.request.SigninRequest;
import com.example.team5project.domain.auth.dto.request.SignupRequest;
import com.example.team5project.domain.auth.dto.response.SigninResponse;
import com.example.team5project.domain.auth.dto.response.SignupResponse;
import com.example.team5project.domain.user.entity.User;
import com.example.team5project.domain.user.enums.UserRole;
import com.example.team5project.domain.user.repository.UserRepository;
import com.example.team5project.global.JwtUtil;
import com.example.team5project.global.filter.SecurityConfig;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final SecurityConfig securityconfig;
    private final UserRepository userRepository;

    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {

        // 같은 이메일의 회원 존재 여부 확인.
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new InvalidRequestStateException("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 암호화.
        String encodedPassword = securityconfig.passwordEncoder().encode(signupRequest.getPassword());
        UserRole userRole = UserRole.of("ROLE_USER");

        // 해당 유저 회원가입.
        User user = new User(signupRequest.getEmail(), signupRequest.getName(), encodedPassword, userRole);
        User savedUser = userRepository.save(user);

        return new SignupResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    }

    @Transactional(readOnly = true)
    public SigninResponse login(SigninRequest request) {

        User foundUser = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 존재하지않습니다.")
        );

        if (!securityconfig.passwordEncoder().matches(request.getPassword(), foundUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지않습니다");
        }

        //jwt 발급 및 클라이언트 측으로 반환.
        String bearerJwt = jwtUtil.createToken(foundUser.getId(), foundUser.getEmail(), foundUser.getName(), foundUser.getUserRole());
        String bearerToken = jwtUtil.substringToken(bearerJwt);

        return new SigninResponse(bearerToken);
    }

    @Transactional
    public void withdraw(Long id) { // 로그인을 하고 그 사용자가 회원 탈퇴하게 변경
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 존재하지않습니다.")
        );

        userRepository.delete(user);
    }
}
