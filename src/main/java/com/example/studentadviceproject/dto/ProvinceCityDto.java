package com.example.studentadviceproject.dto;

import lombok.Data;

/**
 * DTO for {@link com.example.studentadviceproject.entity.City}
 */
@Data
public class ProvinceCityDto {
    Long id;
    private String name;
    Long provinceId;
}