package com.sparta.openmind.service;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.entity.UserRoleEnum;
import com.sparta.openmind.repository.BoardRepository;
import com.sparta.openmind.repository.CommentRepository;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;
    private final CommentRepository commentRepo;

    public List<BoardResponseDto> getContent() {

        return repository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();


    }


    public Board findContent(Integer bno) {
        return repository.findById(bno).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
    }


    public BoardResponseDto findContentandComment(Integer bno) {

        Board board = repository.findById(bno).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트가 존재하지 않습니다."));
        return new BoardResponseDto(board);

    }


    @Transactional
    public BoardResponseDto writeContent(BoardRequestDto requestDto, User user) {
        Board board = repository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);
    }


    @Transactional
    public void deleteContent(Integer bno, User user) {
        String id = findContent(bno).getUser().getUsername();
        Board board = findContent(bno);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || id.equals(user.getUsername()))) {
            throw new RejectedExecutionException();
        } else

        repository.delete(board);
    }



    @Transactional
    public BoardResponseDto updateContent(Integer bno, BoardRequestDto requestDto, User user) {
        String id = findContent(bno).getUser().getUsername();
        Board board = findContent(bno);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || id.equals(user.getUsername()))) {
            throw new RejectedExecutionException();
        }

        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());

        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> getAllBoards() {
        List<Board> boardList = repository.findAll();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;

    }

}
