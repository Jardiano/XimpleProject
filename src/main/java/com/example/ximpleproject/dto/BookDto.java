package com.example.ximpleproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String genre;
    private String resume;
    private boolean reserved;

}
