package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.ProvinceDto;
import com.example.studentadviceproject.entity.Province;
import com.example.studentadviceproject.repository.ProvinceRepository;
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
    public Province createProvince(ProvinceDto provinceDto) {
        Province province = modelMapper.map(provinceDto, Province.class);

        return provinceRepository.save(province);
    }

    @Override
    public void deleteProvince(ProvinceDto provinceDto) {
        Province province = modelMapper.map(provinceDto, Province.class);
        provinceRepository.delete(province);
    }

    @Override
    public List<ProvinceDto> getAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceDto> provinceDtos = new ArrayList<>();
        provinces.forEach(province -> {
            ProvinceDto provinceDto = modelMapper.map(province, ProvinceDto.class);
            provinceDtos.add(provinceDto);
        });
        return provinceDtos;
    }

    @Override
    public ProvinceDto getProvinceById(Long id) {
        Province province = provinceRepository.findById(id).get();
        ProvinceDto provinceDto = modelMapper.map(province, ProvinceDto.class);
        return provinceDto;
    }
}
