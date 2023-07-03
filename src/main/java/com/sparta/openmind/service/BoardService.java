package com.sparta.openmind.service;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = boardRepository.save(new Board(requestDto));
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();

        for (Board board : boardList){
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }
}
