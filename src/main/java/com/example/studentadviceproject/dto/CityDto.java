package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.entity.Province;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.studentadviceproject.entity.City}
 */
@Data
public class CityDto {
    Long id;

}