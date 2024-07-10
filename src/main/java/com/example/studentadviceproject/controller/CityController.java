package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.ProvinceCityDto;
import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.dto.CityDto;
import com.example.studentadviceproject.service.CityService;
import com.example.studentadviceproject.service.ProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CityController {

    private final ProvinceService provinceService;
    private final CityService cityService;

    public CityController(ProvinceService provinceService, CityService cityService) {
        this.provinceService = provinceService;
        this.cityService = cityService;
    }

    @GetMapping("cities/new")
    public String newCity(Model model) {
        model.addAttribute("city", new ProvinceCityDto());
        Map<Long, String> provinceDtos = provinceService.getAllProvinces()
                .stream()
                .collect(Collectors.toMap(ProvinceDto::getId, ProvinceDto::getName));
        model.addAttribute("provinces", provinceDtos);
        return "addCity";
    }
    @PostMapping("cities/new")
    public String saveCity(@ModelAttribute("city") ProvinceCityDto provinceCityDto) {
        ProvinceDto provinceDto = provinceService.getProvinceById(provinceCityDto.getProvinceId());
        CityDto cityDto = new CityDto();
        cityDto.setId(provinceCityDto.getId());
        cityDto.setName(provinceCityDto.getName());
        cityService.createCity(cityDto,provinceDto);
        return "redirect:/cities/new";
    }
}
