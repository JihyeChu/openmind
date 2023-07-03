package com.sparta.openmind.contorller;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

}
