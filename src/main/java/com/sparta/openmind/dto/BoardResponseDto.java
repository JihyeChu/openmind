package com.sparta.openmind.dto;

import com.sparta.openmind.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private long id;
    private String username;
    private String title;
    private String contents;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUsername();
        this.title = board.getTitle();
        this.contents=board.getContents();
    }





}
