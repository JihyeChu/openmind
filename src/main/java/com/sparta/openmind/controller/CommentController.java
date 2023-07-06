package com.sparta.openmind.controller;

import com.sparta.openmind.dto.*;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping(value="/api")
@RequiredArgsConstructor

public class CommentController {

    // Comment
    private final CommentService service;


    // Post Comment
    @PostMapping(value="/comment")
    public CommentResponseDto write(@RequestBody CommentRequestDto requestDto, @RequestParam Integer bno, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.writeComment(requestDto,userDetails.getUser(),bno);
    }

    // Delete Comment
    @DeleteMapping("/comment/{cno}")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Integer cno, @RequestParam Integer bno) {
        try {
            service.deleteComment(cno, bno, userDetails.getUser());
            return ResponseEntity.ok().body(new com.sparta.openmind.dto.ApiResponseDto("댓글이 삭제되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new com.sparta.openmind.dto.ApiResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }


    // Put Comment
    @PutMapping("/comment/{cno}")
    public CommentResponseDto update(@PathVariable Integer cno,@RequestParam Integer bno, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateComment(cno,bno,requestDto,userDetails.getUser());
    }

}
