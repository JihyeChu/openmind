package com.sparta.openmind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("수동 등록한 passwordEncoder를 주입 받아와 문자열 암호화")
    void test1() {
        String password = "my password"; //설정한 비밀번호

        //암호화

        String encodePassword = passwordEncoder.encode(password);
        System.out.println("encodePassword = " + encodePassword);

        String inputPassword = "my"; //입력한 비밀번호 -> matches = false 나와야
        boolean matches = passwordEncoder.matches(inputPassword,encodePassword);
        System.out.println("matches = " + matches);

    }
}
