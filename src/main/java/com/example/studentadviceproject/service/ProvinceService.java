package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.ProvinceCompleteDto;
import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.entity.Province;

import java.util.List;

public interface ProvinceService {

    Province createProvince(ProvinceDto provinceDto);

    void deleteProvince(ProvinceCompleteDto provinceCompleteDto);

    List<ProvinceCompleteDto> getAllProvinces();

    ProvinceCompleteDto getProvinceById(Long id);

    ProvinceDto getProvinceWithCities(Long id);
}
