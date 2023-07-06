package com.sparta.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    //admin계정 구분
    String username;
    boolean isAdmin;
}