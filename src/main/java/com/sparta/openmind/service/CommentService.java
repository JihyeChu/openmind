package com.sparta.openmind.service;

import com.sparta.openmind.dto.CommentRequestDto;
import com.sparta.openmind.dto.CommentResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
// import com.sparta.openmind.entity.User;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.repository.CommentRepository;
import com.sparta.openmind.dto.CommentResponseDto;
import com.sparta.openmind.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;
    private final BoardService boardService;


    // Post
    @Transactional
    public CommentResponseDto writeComment(CommentRequestDto requestDto, User user, Integer bno) {


        Board board = boardService.findContent(bno);

        Comment comment = commentRepo.save(new Comment(requestDto, user, board));


        board.addCommentList(comment);

        List<Comment> list = board.getCommentList();
        for (Comment c : list) {
            System.out.println("Comment = " + c.getComment());
        }

        return new CommentResponseDto(comment);


    }

    // Delete
    @Transactional
    public void deleteComment(Integer cno, Integer bno, User user) {
        String id = findComment(cno).getUser().getUsername();
        Board board = boardService.findContent(bno);

        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            commentRepo.deleteById(cno);
            List<Comment> list = board.getCommentList();


            list.removeIf(c -> c.getCno() == cno);

            for (Comment c : list) {
                System.out.println("Comment = " + c.getCno());
            }
        } else {
            System.out.println("삭제 권한이 없습니다.");
        }


    }


    @Transactional
    public CommentResponseDto updateCommnet(Integer cno, Integer bno, CommentRequestDto requestDto, User user) {

        String id = findComment(cno).getUser().getUsername();
        Board board = boardService.findContent(bno);
        Comment comment = findComment(cno);


        if (id.equals(user.getId()) || user.getRole().toString().equals("ADMIN")) {
            List<Comment> list = board.getCommentList();
            comment.update(requestDto);

            return new CommentResponseDto(comment);
        } else {
            System.out.println("수정 권한이 없습니다.");
            return null;
        }


    }


    // Find Comment
    public Comment findComment(Integer cno) {
        return commentRepo.findById(cno).orElseThrow(() ->
                new IllegalArgumentException("해당 메모가 존재하지 않습니다."));
    }

}
