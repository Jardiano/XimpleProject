package com.example.ximpleproject.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @ManyToOne(optional = false)
    private User user;
    @Valid
    @ManyToOne(optional = false)
    private Book book;
    private String text;
    private Integer rating;

}
