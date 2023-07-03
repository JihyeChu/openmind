package com.sparta.openmind.dto;

import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
import com.sparta.openmind.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter

public class BoardResponseDto {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Comment> commentList;

    public BoardResponseDto(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getUser().getId();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.commentList = board.getCommentList();
    }

    public BoardResponseDto(Board board,List<Comment> commentList) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getUser().getId();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.commentList = commentList;
    }
}
