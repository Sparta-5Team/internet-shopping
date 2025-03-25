package com.example.team5project.domain.auth.service;

import com.example.team5project.domain.auth.dto.request.SigninRequest;
import com.example.team5project.domain.auth.dto.request.SignupRequest;
import com.example.team5project.domain.auth.dto.request.UserRequest;
import com.example.team5project.domain.auth.dto.request.WithdrawRequest;
import com.example.team5project.domain.auth.dto.response.SigninResponse;
import com.example.team5project.domain.auth.dto.response.SignupResponse;
import com.example.team5project.domain.user.entity.User;
import com.example.team5project.domain.user.repository.UserRepository;
import com.example.team5project.global.JwtUtil;
import com.example.team5project.global.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {

        // 같은 이메일의 회원 존재 여부 확인.
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new InvalidRequestStateException("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 암호화.
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // 해당 유저 회원가입.
        User user = new User(signupRequest.getEmail(), signupRequest.getName(), encodedPassword);
        User savedUser = userRepository.save(user);

        //jwt 발급 및 클라이언트 측으로 반환.
        String bearerJwt = jwtUtil.createToken(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
        String jwt = jwtUtil.substringToken(bearerJwt);

        return new SignupResponse(jwt);
    }

    @Transactional(readOnly = true)
    public SigninResponse login(SigninRequest request) {

        User foundUser = getUser(request);

        //jwt 발급 및 클라이언트 측으로 반환.
        String bearerJwt = jwtUtil.createToken(foundUser.getId(), foundUser.getEmail(), foundUser.getName());
        String jwt = jwtUtil.substringToken(bearerJwt);

        return new SigninResponse(jwt);
    }

    @Transactional
    public void withdraw(WithdrawRequest request) {

        User foundUser = getUser(request);
        userRepository.delete(foundUser);
    }

    // 공통 로직 처리 메서드.
    private User getUser(UserRequest request) {

        // 이메일을 통해 유저 존재 여부 확인.
        User foundUser = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 이메일의 유저가 존재하지 않습니다.")
        );

        // 존재하는 유저의 비밀번호와 입력한 비밀번호의 일치 여부 확인.
        if (!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 비밀번호입니다.");
        }

        return foundUser;
    }
}
