package com.example.ximpleproject.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Valid
        @ManyToOne(optional = false)
        private Book book;
        @Valid
        @ManyToOne(optional = false)
        private User user;
        private Date startDate;
        private Date endDate;


}
