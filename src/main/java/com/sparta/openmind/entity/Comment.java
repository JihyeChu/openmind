package com.sparta.openmind.entity;

import com.sparta.openmind.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor

public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int cno;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commenter")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_bno")
    private Board board;


    public Comment(CommentRequestDto requestDto, User user, Board board) {
        this.comment = requestDto.getComment();
        this.user = user;
        this.board = board;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

}
