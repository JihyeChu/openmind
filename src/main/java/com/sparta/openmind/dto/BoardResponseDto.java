package com.sparta.openmind.dto;

import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


@Getter

public class BoardResponseDto {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public BoardResponseDto(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getUser().getUsername();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.commentList = board.getCommentList().stream()
                .map(CommentResponseDto::new)
                .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()) // 작성날짜 내림차순
                .toList();
    }

    public BoardResponseDto(Board board,List<Comment> commentList) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getUser().getUsername();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        //this.commentList = commentList;
    }
}
