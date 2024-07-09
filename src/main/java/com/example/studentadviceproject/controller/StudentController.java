package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentAdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.dto.StudentKodeMelli;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final AdvicerService advicerService;

    public StudentController( StudentService studentService, AdvicerService advicerService) {
        this.studentService = studentService;
        this.advicerService = advicerService;
    }



    @GetMapping("/students/new")
    public String addStudent(Model model) {
        model.addAttribute("student", new StudentDto());
        return "addStudent";
    }
    @PostMapping("/students/new")
    public String addStudent(@Valid @ModelAttribute("student") StudentDto studentDto
            ,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
          return "addStudent";
        }
        studentService.createStudent(studentDto);
        return "redirect:/students/new";
    }



    @GetMapping("/students/update")
    public String updateStudent(Model model) {
        model.addAttribute("studentAdvice", new StudentAdviceDto());
        return "updateStudent";
    }
    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute("studentAdvice")StudentAdviceDto studentAdviceDto
            ,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "updateStudent";
        }
        StudentDto studentDto = studentService.getStudentByKodeMelli(studentAdviceDto.getStudentKodeMelli());
        AdviceDto adviceDto = advicerService.getAdviceByKodeMelli(studentAdviceDto.getAdviceKodeMelli());
        studentService.updateStudent(studentDto, adviceDto);
        return "redirect:/students/update";

    }



    @GetMapping("/students/find")
    public String findStudent(Model model) {
        model.addAttribute("studentKodeMelli", new StudentKodeMelli());
        return "findStudent";
    }
    @PostMapping("/students/find")
    public String getStudent(@ModelAttribute("studentKodeMelli") StudentKodeMelli studentKodeMelli
            , HttpSession session, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "findStudent";
        }
        session.setAttribute("student"
                ,studentService.getStudentByKodeMelli(studentKodeMelli.getStudentKodeMelli()));

        return "redirect:/students/find/student";
    }
    @GetMapping("/students/find/student")
    public String getStudent(Model model,HttpSession session) {
        model.addAttribute("student",session.getAttribute("student"));
        StudentDto studentDto = (StudentDto) session.getAttribute("student");
        System.out.println(studentDto.getKodeMelli());
        return "student";
    }

    @GetMapping("/students/all")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "allStudents";
    }



    @GetMapping("/students/delete")
    public String deleteStudent(Model model) {
        model.addAttribute("studentKodeMelli", new StudentKodeMelli());
        return "deleteStudent";
    }
    @PostMapping("/students/delete")
    public String deleteStudent(@ModelAttribute("studentKodeMelli") StudentKodeMelli studentKodeMelli,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "deleteStudent";
        }
        studentService.deleteStudent(studentKodeMelli.getStudentKodeMelli());
        return "redirect:/students/all";
    }

}
