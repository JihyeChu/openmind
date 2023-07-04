package com.sparta.openmind.dto;

import com.sparta.openmind.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    //게시판 제목
    private String title;
    //게시판내용
    private String contents;
    //게시판 수정내용
    private String newcontents;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.newcontents = board.getNewcontents();
    }
}