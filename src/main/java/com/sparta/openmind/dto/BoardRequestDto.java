package com.sparta.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private long id;
    private String username;
    private String title;
    private String contents;

}
