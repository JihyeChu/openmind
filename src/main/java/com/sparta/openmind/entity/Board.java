package com.sparta.openmind.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor

public class Board extends com.sparta.openmind.entity.Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private int bno;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "writer")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();


    public Board(BoardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void addCommentList(Comment comment){
        this.commentList.add(comment);
        comment.setBoard(this);
    }
}
