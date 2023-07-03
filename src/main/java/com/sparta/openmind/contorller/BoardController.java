package com.sparta.openmind.contorller;

import com.sparta.openmind.dto.BoardRequestDto;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.security.UserDetailsImpl;
import com.sparta.openmind.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/board")
    public String geeBoard(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        System.out.println("user.getUsername() = " + user.getUsername());
        return "redirect:/";
    }


}
