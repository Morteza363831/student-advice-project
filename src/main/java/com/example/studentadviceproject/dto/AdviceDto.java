package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.entity.Gender;
import com.example.studentadviceproject.validation.ValidNationalID;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
public class AdviceDto implements Serializable {
    @NotNull(message = "id cant be null")
    long id;

    @NotNull(message = "kode melli cant be null")
    @ValidNationalID(message = "kode melli is incorrect")
    String kodeMelli;

    @Size(min = 6,max = 24, message = "password size must be between 6 and 24")
    @NotBlank(message = "please enter your password")
    String password;

    @NotBlank(message = "please enter your first name")
    @Size(min = 2, max = 20, message = "first name must be between 2 and 20")
    String firstName;

    @NotBlank(message = "enter your last name")
    @Size(min = 2, max = 20, message = "last name must be between 2 and 20")
    String lastName;

    //@Pattern(regexp = "^male|female$", message = "male or female")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @NotNull(message = "age cant be null")
    @Range(min = 30, max = 70, message = "age must be between 30 and 70")
    int age;
}