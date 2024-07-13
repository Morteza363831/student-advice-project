package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.CityDto;
import com.example.studentadviceproject.dto.ProvinceCompleteDto;
import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.entity.City;
import com.example.studentadviceproject.entity.Province;
import com.example.studentadviceproject.repository.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ModelMapper modelMapper) {
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Province createProvince(ProvinceCompleteDto provinceCompleteDto) {
        Province province = modelMapper.map(provinceCompleteDto, Province.class);

        return provinceRepository.save(province);
    }

    @Override
    public void deleteProvince(ProvinceCompleteDto provinceCompleteDto) {
        Province province = modelMapper.map(provinceCompleteDto, Province.class);
        provinceRepository.delete(province);
    }

    @Override
    @Transactional
    public List<ProvinceCompleteDto> getAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceCompleteDto> provinceCompleteDtos = new ArrayList<>();
        provinces.forEach(province -> {
            ProvinceCompleteDto provinceCompleteDto = modelMapper.map(province, ProvinceCompleteDto.class);
            provinceCompleteDtos.add(provinceCompleteDto);
        });
        return provinceCompleteDtos;
    }

    @Override
    public ProvinceCompleteDto getProvinceById(Long id) {
        Province province = provinceRepository.findById(id).get();
        ProvinceCompleteDto provinceCompleteDto = modelMapper.map(province, ProvinceCompleteDto.class);
        List<CityDto> cityDtos = new ArrayList<>();
        for(City city : province.getCities()) {

            CityDto cityDto = modelMapper.map(city, CityDto.class);
            cityDtos.add(cityDto);
        }
        provinceCompleteDto.setCities(cityDtos);
        return provinceCompleteDto;
    }

    @Override
    public ProvinceDto getProvinceWithCities(Long id) {
        Province province = provinceRepository.findById(id).get();
        ProvinceDto provinceDto = modelMapper.map(province, ProvinceDto.class);
        return provinceDto;
    }
}
