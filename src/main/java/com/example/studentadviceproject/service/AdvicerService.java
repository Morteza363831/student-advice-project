package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Advice;

import java.util.List;

public interface AdvicerService {

    Advice createAdvice(AdviceDto adviceDto);

    List<AdviceDto> getAllAdvice();

    Advice updateAdvice(AdviceDto adviceDto, StudentDto studentDto);

    void deleteAdvice(long adviceId);

    AdviceDto getAdviceById(long adviceId);

}
