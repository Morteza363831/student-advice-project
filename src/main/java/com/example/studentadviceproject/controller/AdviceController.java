package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.AdviceKodeMelli;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.StudentService;
import jakarta.servlet.http.HttpSession;
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



    @GetMapping("/advices/new")
    public String addAdvice(Model model) {
        model.addAttribute("advice", new AdviceDto());
        return "addAdvice";
    }
    @PostMapping("/advices/new")
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



    @GetMapping("/advices/find")
    public String findAdvice(Model model) {
        model.addAttribute("adviceKodeMelli",new AdviceKodeMelli());
        return "findAdvice";
    }
    @PostMapping("advices/find")
    public String getAdvice(@Valid @ModelAttribute("adviceKodeMelli") AdviceKodeMelli adviceKodeMelli,
                            HttpSession session, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError.getDefaultMessage());
            }
        }

        session.setAttribute("advice",
                advicerService.getAdviceByKodeMelli(adviceKodeMelli.getAdviceKodeMelli()));

        return "redirect:/advices/find/advice";
    }
    @GetMapping("advices/find/advice")
    public String getAdvice(HttpSession session, Model model) {
        model.addAttribute("advice", session.getAttribute("advice"));
        return "advice";
    }



    @GetMapping("advices/all")
    public String getAllAdvice(Model model) {
        model.addAttribute("advices", advicerService.getAllAdvice());
        return "allAdvices";
    }



    @GetMapping("/advices/delete")
    public String deleteStudent(Model model) {
        model.addAttribute("adviceKodeMelli", new AdviceKodeMelli());
        return "deleteAdvice";
    }
    @PostMapping("/advices/delete")
    public String deleteStudent(@Valid @ModelAttribute("adviceKodeMelli") AdviceKodeMelli adviceKodeMelli) {
        studentService.deleteStudent(adviceKodeMelli.getAdviceKodeMelli());
        return "redirect:/advices/all";
    }


}
