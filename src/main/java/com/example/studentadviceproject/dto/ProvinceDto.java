package com.example.studentadviceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProvinceDto {

    long id;
    @NotBlank(message = "please enter province name")
    String name;

}
