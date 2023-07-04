package com.sparta.openmind.service;


import com.sparta.openmind.dto.BoardNewcontentRequestDto;
import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = boardRepository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);
    }

   @Transactional
    public BoardResponseDto updateBoard(Long id, BoardNewcontentRequestDto requestDto) {
        String content = requestDto.getNewcontents();
        if(content==null){
            throw new IllegalArgumentException("수정하기 위해서 1글자 이상 입력해주세요.");
        }

        Board board = boardRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 상품을 찾을 수 없습니다.")
        );
        board.update(requestDto);
        return new BoardResponseDto(board);
    }


    public List<BoardResponseDto> getBoards(User user) {
        List<Board> boardList = boardRepository.findAllByUser(user);
        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }

    public List<BoardResponseDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;


    }


}



