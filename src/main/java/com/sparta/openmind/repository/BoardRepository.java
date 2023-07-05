package com.sparta.openmind.repository;

import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import com.sparta.openmind.dto.BoardResponseDto;
import com.sparta.openmind.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByOrderByModifiedAtDesc();

}
