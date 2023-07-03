package com.sparta.openmind.contorller;

import com.sparta.openmind.dto.SignupRequestDto;
import com.sparta.openmind.service.UserService;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/user/login-page")
    public String loginPage() {
        return null;
    }

    @GetMapping("api/user/signup")
    public String signupPage() {
        return null;
    }

    @PostMapping("api/user/signup")
    public String signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
}

