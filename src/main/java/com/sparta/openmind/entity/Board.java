package com.sparta.openmind.entity;
// 사용자가 올린 게시글의 조회, 작성, 수정, 삭제

import com.sparta.openmind.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board")
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;


    public Board(BoardRequestDto requestDto) {
        this.username =requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();

    }

}
