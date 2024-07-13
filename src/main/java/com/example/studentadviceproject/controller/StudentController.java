package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.*;
import com.example.studentadviceproject.service.AdvicerService;
import com.example.studentadviceproject.service.CityService;
import com.example.studentadviceproject.service.ProvinceService;
import com.example.studentadviceproject.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final AdvicerService advicerService;
    private final ProvinceService provinceService;
    private final CityService cityService;


    public StudentController(StudentService studentService, AdvicerService advicerService,
                             ProvinceService provinceService, CityService cityService) {

        this.studentService = studentService;
        this.advicerService = advicerService;
        this.provinceService = provinceService;
        this.cityService = cityService;
    }

    @ModelAttribute("student")
    public StudentDto studentDto() {
        return new StudentDto();
    }

    @GetMapping("/students/new")
    public String addStudent(Model model) {
        Map<Long,String> provinceDtos = provinceService.getAllProvinces()
                .stream()
                .collect(Collectors.toMap(ProvinceCompleteDto::getId, ProvinceCompleteDto::getName));
        model.addAttribute("provinces", provinceDtos);

        return "addStudent";
    }
    @PostMapping("/students/new")
    public String addStudent(Model model,@Valid @ModelAttribute("student") StudentDto studentDto
            ,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
          return "addStudent";
        }
        ProvinceCompleteDto provinceCompleteDto = provinceService.getProvinceById(studentDto.getProvinceId());
        if(studentDto.getCityId() == null) {
            List<CityDto> cityDtos = provinceCompleteDto.getCities();
            Map<Long,String> provinceMap = new HashMap<>();
            provinceMap.put(provinceCompleteDto.getId(), provinceCompleteDto.getName());
            model.addAttribute("provinces", provinceMap);
            model.addAttribute("cities", cityDtos);
            return "addStudent";
        }
        System.out.println(studentDto.getProvinceId() + "provinceDto");
        CityDto cityDto = cityService.getCityById(studentDto.getCityId());
        studentService.createStudent(studentDto, provinceCompleteDto,cityDto);

        return "redirect:/students/new";
    }



    @GetMapping("/students/update")
    public String updateStudent(Model model) {
        model.addAttribute("studentAdvice", new StudentAdviceDto());
        return "updateStudent";
    }
    @PostMapping("/students/update")
    public String updateStudent(@Valid @ModelAttribute("studentAdvice")StudentAdviceDto studentAdviceDto
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
    public String getStudent(@Valid @ModelAttribute("studentKodeMelli") StudentKodeMelli studentKodeMelli
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
    public String deleteStudent(@Valid @ModelAttribute("studentKodeMelli") StudentKodeMelli studentKodeMelli,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "deleteStudent";
        }
        studentService.deleteStudent(studentKodeMelli.getStudentKodeMelli());
        return "redirect:/students/all";
    }

}
