package com.sparta.openmind.entity;

import com.sparta.openmind.dto.BoardNewcontentRequestDto;
import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "board") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String newcontents;



    //N:1 단방향 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    public Board(BoardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user=user;
    }

    public void update(BoardNewcontentRequestDto requestDto) {
        this.newcontents= requestDto.getNewcontents();
    }

}