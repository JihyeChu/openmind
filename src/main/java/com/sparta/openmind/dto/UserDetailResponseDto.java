package com.sparta.openmind.dto;

import com.sparta.openmind.entity.User;
import com.sparta.openmind.entity.UserDetail;
import lombok.Getter;

@Getter
public class UserDetailResponseDto {
    private Long id;
    private String nickname;
    private String userdetail;



    public UserDetailResponseDto(UserDetail userDetail) {
        this.id = userDetail.getId();
        this.nickname = userDetail.getNickname();
        this.userdetail = userDetail.getUserdetail();

    }
}