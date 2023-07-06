package com.sparta.openmind.contorller;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    //게시글 등록하기
    @PostMapping("/board")
    @ResponseBody
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //응답보내기
        return boardService.createBoard(requestDto, userDetails.getUser());
    }

    //게시글 조회하기
    @GetMapping("/board")
    public List<BoardResponseDto> getBoards(@AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User user = userDetails.getUser();
        System.out.println("user.getUsername() = " + user.getUsername());
        return boardService.getBoards(userDetails.getUser());
    }

//    @PutMapping("/board/{id}")
//    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//    }


//    @GetMapping("/board")
//    public String getBoards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        User user = userDetails.getUser();
//        System.out.println("user.getUsername() = " + user.getUsername());
//        return "redirect:/";
//    }


}
