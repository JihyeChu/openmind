package com.sparta.openmind.service;

import com.sparta.openmind.dto.CommentRequestDto;
import com.sparta.openmind.dto.CommentResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.entity.Comment;
// import com.sparta.openmind.entity.User;
import com.sparta.openmind.entity.User;
import com.sparta.openmind.entity.UserRoleEnum;
import com.sparta.openmind.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

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

    // Delete - Postman msg
    @Transactional
    public void deleteComment(Integer cno, Integer bno, User user) {
        String id = findComment(cno).getUser().getUsername();
        Board board = boardService.findContent(bno);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || id.equals(user.getUsername()))) {
            throw new RejectedExecutionException();
        } else

            commentRepo.deleteById(cno);

            List<Comment> list = board.getCommentList();


            list.removeIf(c -> c.getCno() == cno);

            for (Comment c : list) {
                 System.out.println("Comment = " + c.getCno());
        }
    }



    @Transactional
    public CommentResponseDto updateComment(Integer cno, Integer bno, CommentRequestDto requestDto, User user) {

        String id = findComment(cno).getUser().getUsername();
        Board board = boardService.findContent(bno);
        Comment comment = findComment(cno);


        if (id.equals(user.getUsername()) || user.getRole().toString().equals("ADMIN")) {
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
