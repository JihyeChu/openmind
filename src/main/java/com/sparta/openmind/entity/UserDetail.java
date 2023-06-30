package com.sparta.openmind.entity;

import com.sparta.openmind.dto.UserDetailRequestDto;
import com.sparta.openmind.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class UserDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;
    @Column(name = "userdetail", nullable = false, length = 50)
    private String userdetail;

    public UserDetail(UserDetailRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.userdetail = requestDto.getUserdetail();
    }

    public void update(UserDetailRequestDto requestDto) {
        this.nickname= requestDto.getNickname();
        this.userdetail = requestDto.getUserdetail();
    }
}