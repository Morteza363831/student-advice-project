package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.ProvinceCityDto;
import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.entity.City;
import com.example.studentadviceproject.dto.CityDto;

import java.util.List;

public interface CityService {

    City createCity(CityDto cityDto, ProvinceDto provinceDto);
    void deleteCity(ProvinceCityDto provinceCityDto);

    CityDto getCityById(Long id);
}
