package com.sparta.openmind.service;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.repository.BoardRepository;
import com.sparta.openmind.repository.CommentRepository;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    public ResponseEntity<List<BoardResponseDto>> findContentandComment(Integer bno) {

        List<BoardResponseDto> list = repository.findByBno(bno);
        return new ResponseEntity<List<BoardResponseDto>>(list, HttpStatus.OK);

    }


    @Transactional
    public BoardResponseDto writeContent(BoardRequestDto requestDto, User user) {
        Board board = repository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);
    }


    @Transactional
    public void deleteContent(Integer bno, User user) {

        String id = findContent(bno).getUser().getId();


        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            repository.deleteById(bno);
        } else {
            System.out.println("삭제할 권한이 없습니다.");
        }


    }


    @Transactional
    public BoardResponseDto updateContent(Integer bno, BoardRequestDto requestDto, User user) {

        Board board = findContent(bno);
        String id = findContent(bno).getUser().getId();

        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            board.update(requestDto);
            return new BoardResponseDto(board);
        } else {
            System.out.println("수정할 권한이 없습니다.");
            return null;
        }
    }

}
