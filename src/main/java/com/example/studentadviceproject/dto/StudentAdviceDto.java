package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.validation.ValidNationalID;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentAdviceDto {

    @NotNull(message = "student kode melli cant be null")
    @ValidNationalID(message = "student kode melli is incorrect")
    String studentKodeMelli;
    @NotNull(message = "advice kode melli cant be null")
    @ValidNationalID(message = "advice kode melli is incorrect")
    String adviceKodeMelli;
}
