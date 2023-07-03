package com.sparta.openmind.controller;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
import com.sparta.openmind.service.BoardService;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    // Get all
    @GetMapping("/board")
    public List<BoardResponseDto> getContent(){
        return service.getContent();
    }

    // Get
    @GetMapping("/board/{bno}")
    public ResponseEntity<List<BoardResponseDto>> getContentByBno(@PathVariable Integer bno){
        Board board =  service.findContent(bno);
        BoardResponseDto responseDto = new BoardResponseDto(board);
        return BoardResponseDto;

        return service.findContentandComment(bno);

    }


    // Post
    @PostMapping("/board")
    public BoardResponseDto write(@RequestBody BoardRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.writeContent(requestDto,userDetails.getUser());
    }

    // Delete
    @DeleteMapping("/board/{bno}")
    public String delete(@PathVariable Integer bno,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        service.deleteContent(bno,userDetails.getUser());
        return "삭제가 완료되었습니다.";

    }

    // Put
    @PutMapping("/board/{bno}")
    public BoardResponseDto update(@PathVariable Integer bno,@RequestBody BoardRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateContent(bno,requestDto,userDetails.getUser());
    }

}
