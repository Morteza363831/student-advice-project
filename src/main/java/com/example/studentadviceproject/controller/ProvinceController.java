package com.example.studentadviceproject.controller;

import com.example.studentadviceproject.dto.ProvinceCompleteDto;
import com.example.studentadviceproject.service.ProvinceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/provinces/new")
    public String newProvince(Model model) {
        model.addAttribute("province", new ProvinceCompleteDto());
        return "addProvince";
    }
    @PostMapping("/provinces/new")
    public String newProvince(@Valid @ModelAttribute("province") ProvinceCompleteDto provinceCompleteDto,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "addProvince";
        }
        provinceService.createProvince(provinceCompleteDto);
        return "redirect:/provinces/new";
    }



}
