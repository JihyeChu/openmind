package com.sparta.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto { //naveropenapi로 보내지는 데이
    // 게시판이름
    private String title;
    // 게시판글Product
    private String contents;

}