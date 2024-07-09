package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentAdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
          List<ObjectError> list = bindingResult.getAllErrors().stream().toList();
          for (ObjectError objectError : list) {
              System.out.println(objectError.getDefaultMessage());
          }
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
    public String updateStudent(@ModelAttribute("studentAdvice")StudentAdviceDto studentAdviceDto) {
        StudentDto studentDto = studentService.getStudentByKodeMelli(studentAdviceDto.getStudentKodeMelli());
        AdviceDto adviceDto = advicerService.getAdviceByKodeMelli(studentAdviceDto.getAdviceKodeMelli());
        studentService.updateStudent(studentDto, adviceDto);
        return "redirect:/students/update";

    }


    @GetMapping("students/{kodeMelli}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String kodeMelli) {
        return new ResponseEntity<StudentDto>(studentService.getStudentByKodeMelli(kodeMelli), HttpStatus.OK);
    }

    @GetMapping("/students/all")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "allStudents";
    }

    @GetMapping("students/all/{adviceId}")
    public ResponseEntity<List<StudentDto>> getAllStudentsByAdivceId(@PathVariable Long adviceId) {
        List<StudentDto> studentList = studentService.getStudentsByAdviceId(adviceId);
        return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
    }

}
