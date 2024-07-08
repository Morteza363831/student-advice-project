package com.example.studentadviceproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String gender;
    private String age;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "advice_id")
    private Advice advice;
}
