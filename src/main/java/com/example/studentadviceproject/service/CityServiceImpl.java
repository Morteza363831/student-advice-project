package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.ProvinceCityDto;
import com.example.studentadviceproject.dto.ProvinceCompleteDto;
import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.entity.City;
import com.example.studentadviceproject.dto.CityDto;
import com.example.studentadviceproject.entity.Province;
import com.example.studentadviceproject.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public City createCity(CityDto cityDto, ProvinceDto provinceDto) {
        Province province = modelMapper.map(provinceDto, Province.class);
        City city = modelMapper.map(cityDto, City.class);
        city.setProvince(province);
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(ProvinceCityDto provinceCityDto) {
        City city = modelMapper.map(provinceCityDto, City.class);
        cityRepository.delete(city);
    }


    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id).get();
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        return cityDto;
    }
}
