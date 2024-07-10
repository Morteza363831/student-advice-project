package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.validation.ValidNationalID;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

@Data
public class StudentDto {
    @NotNull(message = "id cant be null")
    long id;

    @NotNull(message = "kode melli cant be null")
    @ValidNationalID(message = "incorrect kode melli")
    String kodeMelli;

    @Size(min = 2, max = 20, message = "first name must be between 2 and 20")
    @NotBlank(message = "please enter your first name")
    String firstName;

    @Size(min = 2, max = 20, message = "last name must be between 2 and 20")
    @NotBlank(message = "please enter your last name")
    String lastName;

    //@Size(min = 4, max = 6, message = "gender must be between 4 and 6")
    @Pattern(regexp = "^male|female$", message = "male or female")
    @NotBlank(message = "please enter your gender")
    String gender;

    @Range(min = 18, max = 22, message = "age must be between 18 and 22")
    @Positive(message = "age must be positive")
    @NotNull(message = "age cant be null")
    int age;

    @Email(message = "invalid email address , please enter a proper email")
    @NotBlank(message = "email cant be null or empty . please enter your email")
    String email;

    @Size(min = 11, max = 11, message = "phone number cant have more than 11 digits")
    @Pattern(regexp = "^09\\d{9}$")
    String phone;


    Long provinceId;
    Long cityId;



}