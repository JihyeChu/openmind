package com.sparta.openmind.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    //size, pattern 은 살려놓을 예정
    @NotBlank
//    @Size(min = 4, max = 10, message = "사용자 이름은 4글자에서 10자 사이로 입력해주세요")
//    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자와 숫자만 입력 가능합니다.")
    private String username;

    @NotBlank
//    @Size(min = 8, max = 15, message = "비밀번호는 8자에서 15자 사이로 입력해주세요")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]*$", message = "소문자, 대문자, 숫자, 특수문자 조합의 비밀번호만 입력 가능합니다.")
    private String password;

    @NotBlank
    private String email;

    // 관리자 계정인지 아닌지 파악해야함
    @NotBlank
//    @Pattern(regexp = "ADMIN|USER", message = "권한은 ADMIN 혹은 USER만 입력 가능합니다.")
    private boolean admin = false;
    private String adminToken = ""; // 관리자 계정이라면 필요한 토큰 입력

}
