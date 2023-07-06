package com.sparta.openmind.controller;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
import com.sparta.openmind.repository.CommentRepository;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.BoardService;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.service.BoardService;
import com.sparta.openmind.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

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
    public BoardResponseDto getContentByBno(@PathVariable Integer bno){
        BoardResponseDto responseDto = service.findContentandComment(bno);
        return responseDto;
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

    /*@DeleteMapping("/comments/{id}")
    public ResponseEntity<com.sparta.openmind.dto.ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            CommentService.deleteComment(id, userDetails.getUser());
            return ResponseEntity.ok().body(new com.sparta.openmind.dto.ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new com.sparta.openmind.dto.ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }*/

    // Put
    @PutMapping("/board/{bno}")
    public BoardResponseDto update(@PathVariable Integer bno,@RequestBody BoardRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateContent(bno,requestDto,userDetails.getUser());
    }



}
