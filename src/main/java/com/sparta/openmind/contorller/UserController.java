package com.sparta.openmind.contorller;

import com.sparta.openmind.dto.SignupRequestDto;
import com.sparta.openmind.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
//@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    // postman 사용하려면 @RequestBody, @ResponseBody 필요
    @PostMapping("/user/signup")
    @ResponseBody
    public String signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:api/user/signup";
        }
        userService.signup(requestDto);
        return "redirect:api/user/login-page";
    }


}
