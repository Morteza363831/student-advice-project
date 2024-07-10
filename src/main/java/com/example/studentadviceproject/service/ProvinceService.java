package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.entity.Province;

import java.util.List;

public interface ProvinceService {

    Province createProvince(ProvinceDto provinceDto);

    void deleteProvince(ProvinceDto provinceDto);

    List<ProvinceDto> getAllProvinces();

    ProvinceDto getProvinceById(Long id);
}
