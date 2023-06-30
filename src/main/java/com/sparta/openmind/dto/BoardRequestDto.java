package com.sparta.openmind.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private long id;
    private String username;
    private String title;
    private String contents;

}
