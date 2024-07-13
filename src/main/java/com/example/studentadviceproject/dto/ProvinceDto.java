package com.example.studentadviceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProvinceDto {

    @NotNull(message = "province id cant be null")
    Long id;
    @NotBlank(message = "please enter province name")
    String name;

}
