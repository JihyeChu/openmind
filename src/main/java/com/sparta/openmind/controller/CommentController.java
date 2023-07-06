package com.sparta.openmind.controller;

import com.sparta.openmind.dto.CommentRequestDto;
import com.sparta.openmind.dto.CommentResponseDto;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
@RequiredArgsConstructor

public class CommentController {

    // Comment
    private final CommentService service;


    // Put Comment
    @PostMapping(value="/comment")
    public CommentResponseDto write(@RequestBody CommentRequestDto requestDto, @RequestParam Integer bno, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.writeComment(requestDto,userDetails.getUser(),bno);
    }

    // Delete Comment
    @DeleteMapping("/comment/{cno}")
    public CommentResponseDto delete(@PathVariable Integer cno,@RequestParam Integer bno,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("삭제되었습니다.");
        return service.deleteComment(cno,bno,userDetails.getUser());

    }

    // Put Comment
    @PutMapping("/comment/{cno}")
    public CommentResponseDto update(@PathVariable Integer cno,@RequestParam Integer bno, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateComment(cno,bno,requestDto,userDetails.getUser());
    }

}
