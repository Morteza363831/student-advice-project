package com.example.studentadviceproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kodeMelli;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
}


