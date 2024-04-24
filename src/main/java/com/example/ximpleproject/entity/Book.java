package com.example.ximpleproject.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String resume;
    private boolean reserved;
}
