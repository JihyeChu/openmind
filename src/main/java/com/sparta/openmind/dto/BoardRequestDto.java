package com.sparta.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Validation 적용할 수 있음.
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private long id;
    //@NotBlank 같은...
    private String username;
    private String title;
    private String contents;

}
