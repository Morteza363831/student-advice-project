package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final AdvicerService advicerService;

    public StudentController(StudentService studentService,AdvicerService advicerService) {
        this.studentService = studentService;
        this.advicerService = advicerService;
    }

    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<Student>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @PostMapping("students/{studentId}/{adviceId}")
    public Student updateStudent(@PathVariable Long studentId, @PathVariable Long adviceId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        AdviceDto adviceDto = advicerService.getAdviceById(adviceId);
        advicerService.updateAdvice(adviceDto,studentDto);
        return studentService.updateStudent(studentDto, adviceDto);

    }


    @GetMapping("students/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) {
        return new ResponseEntity<StudentDto>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @GetMapping("students/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return new ResponseEntity<List<StudentDto>>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("students/all/{adivceId}")
    public ResponseEntity<List<StudentDto>> getAllStudentsByAdivceId(@PathVariable Long adivceId) {
        List<StudentDto> studentList = studentService.getStudentsByAdviceId(adivceId);
        return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
    }
}
