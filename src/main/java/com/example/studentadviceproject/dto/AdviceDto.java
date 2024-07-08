package com.example.studentadviceproject.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdviceDto implements Serializable {
    long id;
    String firstName;
    String lastName;
    int age;
}