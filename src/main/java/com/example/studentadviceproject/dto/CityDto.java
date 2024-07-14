package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.entity.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link City}
 */
@Data
public class CityDto implements Serializable {
    long id;
    @Size(message = "bad size for city", min = 2, max = 20)
    @NotBlank(message = "bad name for city")
    String name;
}