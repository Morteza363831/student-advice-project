package com.example.studentadviceproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String lastName;
    private String gender;
    private String firstName;
    private int age;
   // @Pattern(regexp = "@gmail.com", message = "email must have @gmail.com")
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "advice_id")
    private Advice advice;
}
