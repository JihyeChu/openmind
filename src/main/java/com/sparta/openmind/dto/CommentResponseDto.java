package com.sparta.openmind.dto;

import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class CommentResponseDto {

    private Integer cno;
    private Integer bno;
    private String comment;
    private String commenter;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.cno = comment.getCno();
        this.bno = comment.getBoard().getBno();
        this.comment = comment.getComment();
        this.commenter = comment.getUser().getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
