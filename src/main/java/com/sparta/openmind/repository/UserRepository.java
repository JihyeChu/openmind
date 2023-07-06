package com.sparta.openmind.repository;

import com.sparta.openmind.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //사용자이름으로 조회하기
    Optional<User> findByUsername(String username);
    //이메일로 조회하기
    Optional<User> findByEmail(String email);
}