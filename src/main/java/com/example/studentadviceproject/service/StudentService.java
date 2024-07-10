package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.CityDto;
import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentDto studentDto, ProvinceDto provinceDto, CityDto cityDto);

    Student updateStudent(StudentDto studentDto, AdviceDto adviceDto);

    void deleteStudent(String kodeMelli);

    List<StudentDto> getAllStudents();

    StudentDto getStudentByKodeMelli(String kodeMelli);

    List<StudentDto> getStudentsByAdviceId(Long adviceId);
}
