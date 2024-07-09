package com.example.studentadviceproject.dto;

import com.example.studentadviceproject.validation.ValidNationalID;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdviceKodeMelli {


    @NotNull(message = "kode melli cant be null")
    @ValidNationalID(message = "kode melli is incorrect")
    String adviceKodeMelli;
}
