package com.example.studentadviceproject.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDto implements Serializable {
    long id;
    String firstName;
    String lastName;
    String gender;
    String age;
    String email;
    String phone;
}