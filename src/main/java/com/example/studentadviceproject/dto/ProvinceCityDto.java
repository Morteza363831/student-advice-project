package com.example.studentadviceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for {@link com.example.studentadviceproject.entity.City}
 */
@Data
public class ProvinceCityDto {
    @NotNull(message = "city id cant be null")
    Long id;
    @NotBlank(message = "please enter city name")
    private String name;
    @NotNull(message = "province id cant be null")
    Long provinceId;
}