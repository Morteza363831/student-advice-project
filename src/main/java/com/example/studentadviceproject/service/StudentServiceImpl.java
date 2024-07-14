package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.CityDto;
import com.example.studentadviceproject.dto.ProvinceCompleteDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Advice;
import com.example.studentadviceproject.entity.City;
import com.example.studentadviceproject.entity.Province;
import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper,
                              PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Student createStudent(StudentDto studentDto, ProvinceCompleteDto provinceCompleteDto, CityDto cityDto) {
        Province province = modelMapper.map(provinceCompleteDto, Province.class);
        City city = modelMapper.map(cityDto, City.class);
        Student student = modelMapper.map(studentDto, Student.class);
        student.setProvince(province);
        student.setCity(city);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(StudentDto studentDto, AdviceDto adviceDto) {
        Advice advice = modelMapper.map(adviceDto, Advice.class);
        Student student = modelMapper.map(studentDto, Student.class);
        student.setAdvice(advice);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(String kodeMelli) {
        studentRepository.deleteStudentByKodeMelli(kodeMelli);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : students) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    @Override
    public StudentDto getStudentByKodeMelli(String kodeMelli) {
        StudentDto studentDto = modelMapper.map(studentRepository.findStudentsByKodeMelli(kodeMelli), StudentDto.class);
        System.out.println(studentDto.getId());
        return studentDto;
    }

    @Override
    public List<StudentDto> getStudentsByAdviceId(Long adviceId) {
        List<Student> students = studentRepository.findStudentsByAdviceId(adviceId);
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : students) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }


}
