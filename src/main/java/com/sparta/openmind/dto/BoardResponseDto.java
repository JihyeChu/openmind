package com.sparta.openmind.dto;

import lombok.Getter;

@Getter
public class BoardResponseDto {
    private long id;
    private String username;
    private String title;
    private String contents;

    // ()안을 board 객체로 변경해야함

//    public BoardResponseDto(long id, String username, String title, String contents) {
//        this.id = id;
//        this.username = username;
//        this.title = title;
//        this.contents = contents;
//    }
}
