package com.example.studentadviceproject.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link City}
 */
@Value
public class CityDto implements Serializable {
    @NotNull(message = "city id cant be null")
    Long id;
    @Size(message = "bad size for city", min = 2, max = 20)
    @NotBlank(message = "bad name for city")
    String name;
}