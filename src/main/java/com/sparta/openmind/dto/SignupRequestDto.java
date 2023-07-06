package com.sparta.openmind.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String username;
    private String password;
    private String email;

    // 관리자 계정인지 아닌지 파악해야함
    private boolean admin = false;
    private String adminToken = ""; // 관리자 계정이라면 필요한 토큰 입력

}
