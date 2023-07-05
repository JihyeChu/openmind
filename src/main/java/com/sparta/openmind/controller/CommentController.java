package com.sparta.openmind.controller;

import com.sparta.openmind.dto.CommentRequestDto;
import com.sparta.openmind.dto.CommentResponseDto;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class CommentController {

    // Comment
    private final CommentService service;


    // Put Comment
    @PostMapping("/comment")
    public CommentResponseDto write(@RequestBody CommentRequestDto requestDto, @RequestParam Integer bno, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.writeComment(requestDto,userDetails.getUser(),bno);
    }

    // Delete Comment
    @DeleteMapping("/comment/{cno}")
    public String delete(@PathVariable Integer cno,@RequestParam Integer bno,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        service.deleteComment(cno,bno,userDetails.getUser());
        return "삭제가 완료되었습니다.";

    }

    // Put Comment
    @PutMapping("/comment/{cno}")
    public CommentResponseDto update(@PathVariable Integer cno,@RequestParam Integer bno, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.updateCommnet(cno,bno,requestDto,userDetails.getUser());
    }

}
