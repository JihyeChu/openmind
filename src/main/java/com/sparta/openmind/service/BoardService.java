package com.sparta.openmind.service;

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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = boardRepository.save(new Board(requestDto, user));
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

}

