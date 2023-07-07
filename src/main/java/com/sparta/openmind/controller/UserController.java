package com.sparta.openmind.controller;

import com.sparta.openmind.dto.LoginRequestDto;
import com.sparta.openmind.dto.SignupRequestDto;
import com.sparta.openmind.dto.UserInfoDto;
import com.sparta.openmind.entity.UserRoleEnum;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @GetMapping("/user/login-page")
    public String loginPage() {
        return "회원정보가 중복되었습니다.";
    }


    // 지혜님 PostMapping (html x)
    @PostMapping("/user/signup")
    //@ResponseBody
    public String signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
    // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "회원가입이 실패하였습니다.";
        }

        userService.signup(requestDto);
        return "회원가입이 성공하였습니다.";
    }


    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }
}