package com.sparta.openmind.repository;

import com.sparta.openmind.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByOrderByModifiedAtDesc();
    List<Comment> findByBoard_Bno(Integer bno);

}
