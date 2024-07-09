package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Advice;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdviceController {


    private final AdvicerService advicerService;
    private final StudentService studentService;

    public AdviceController(AdvicerService advicerService, StudentService studentService) {
        this.advicerService = advicerService;
        this.studentService = studentService;
    }

    @GetMapping("advices/new")
    public String addAdvice(Model model) {
        model.addAttribute("advice", new AdviceDto());
        return "addAdvice";
    }

    @PostMapping("advices/new")
    public String addAdvice(@Valid @ModelAttribute("advice") AdviceDto adviceDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError.getDefaultMessage());
            }
        }
        advicerService.createAdvice(adviceDto);
        return "redirect:/advices/new";
    }


    @GetMapping("advices/{adviceId}")
    public ResponseEntity<AdviceDto> getAdvice(@PathVariable String kodeMelli) {
        return new ResponseEntity<AdviceDto>(advicerService.getAdviceByKodeMelli(kodeMelli), HttpStatus.OK);
    }

    @GetMapping("advices/all")
    public String getAllAdvice(Model model) {
        model.addAttribute("advices", advicerService.getAllAdvice());
        return "allAdvices";
    }

    @PostMapping("advices/{adviceId}/{studentId}")
    public ResponseEntity<Advice> updateAdvice(@PathVariable String adviceKodeMelli
            , @PathVariable String studentKodeMelli) {
        AdviceDto adviceDto = advicerService.getAdviceByKodeMelli(adviceKodeMelli);
        StudentDto studentDto = studentService.getStudentByKodeMelli(studentKodeMelli);
        return new ResponseEntity<Advice>(advicerService.updateAdvice(adviceDto,studentDto), HttpStatus.OK);
    }
}
