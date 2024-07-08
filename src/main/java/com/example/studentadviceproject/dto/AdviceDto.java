package com.example.studentadviceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
public class AdviceDto implements Serializable {
    @NotNull(message = "id cant be null")
    long id;

    @NotBlank(message = "please enter your first name")
    @Size(min = 2, max = 20, message = "first name must be between 2 and 20")
    String firstName;

    @NotBlank(message = "enter your last name")
    @Size(min = 2, max = 20, message = "last name must be between 2 and 20")
    String lastName;

    @NotNull(message = "age cant be null")
    @Range(min = 30, max = 70, message = "age must be between 30 and 70")
    int age;
}