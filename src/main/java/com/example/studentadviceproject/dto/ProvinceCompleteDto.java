package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.entity.Province;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * DTO for {@link Province}
 */
@Data
public class ProvinceCompleteDto {
    @NotNull(message = "province id cant be null")
    Long id;
    @Size(message = "invalid size of province name", min = 2, max = 20)
    @NotBlank(message = "province name isnt defined !")
    String name;

    List<CityDto> cities;
}