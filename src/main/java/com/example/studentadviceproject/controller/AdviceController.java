package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Advice;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdviceController {


    private final AdvicerService advicerService;
    private final StudentService studentService;

    public AdviceController(AdvicerService advicerService, StudentService studentService) {
        this.advicerService = advicerService;
        this.studentService = studentService;
    }

    @PostMapping("advices")
    public ResponseEntity<Advice> addAdvice(@Valid @RequestBody AdviceDto adviceDto) {
        return new ResponseEntity<Advice>(advicerService.createAdvice(adviceDto), HttpStatus.OK);
    }

    @GetMapping("advices/{adviceId}")
    public ResponseEntity<AdviceDto> getAdvice(@PathVariable Long adviceId) {
        return new ResponseEntity<AdviceDto>(advicerService.getAdviceById(adviceId), HttpStatus.OK);
    }

    @GetMapping("advices/all")
    public ResponseEntity<List<AdviceDto>> getAllAdvice() {
        return new ResponseEntity<List<AdviceDto>>(advicerService.getAllAdvice(), HttpStatus.OK);
    }

    @PostMapping("advices/{adviceId}/{studentId}")
    public ResponseEntity<Advice> updateAdvice(@PathVariable Long adviceId, @PathVariable Long studentId) {
        AdviceDto adviceDto = advicerService.getAdviceById(adviceId);
        StudentDto studentDto = studentService.getStudentById(studentId);
        return new ResponseEntity<Advice>(advicerService.updateAdvice(adviceDto,studentDto), HttpStatus.OK);
    }
}
