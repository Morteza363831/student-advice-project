package com.example.studentadviceproject.service;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Advice;
import com.example.studentadviceproject.entity.Student;
import com.example.studentadviceproject.repository.AdvicerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdviceServiceImpl implements AdvicerService{

    private final AdvicerRepository advicerRepository;
    private final ModelMapper modelMapper;

    public AdviceServiceImpl(AdvicerRepository advicerRepository, ModelMapper modelMapper) {
        this.advicerRepository = advicerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Advice createAdvice(AdviceDto adviceDto) {
        Advice advice = modelMapper.map(adviceDto, Advice.class);
        return advicerRepository.save(advice);
    }

    @Override
    public List<AdviceDto> getAllAdvice() {
        List<Advice> advices = advicerRepository.findAll();
        List<AdviceDto> adviceDtoList = new ArrayList<>();
        for (Advice advice : advices) {
            adviceDtoList.add(modelMapper.map(advice, AdviceDto.class));
        }
        return adviceDtoList;
    }

    @Override
    public Advice updateAdvice(AdviceDto adviceDto, StudentDto studentDto) {
        Student student = modelMapper.map(adviceDto, Student.class);
        Advice advice = modelMapper.map(adviceDto, Advice.class);
        return advicerRepository.save(advice);

    }

    @Override
    public void deleteAdvice(String kodeMelli) {
        advicerRepository.deleteAdviceByKodeMelli(kodeMelli);
    }

    @Override
    public AdviceDto getAdviceByKodeMelli(String kodeMelli) {
        AdviceDto adviceDto = modelMapper.map(advicerRepository.findAdviceByKodeMelli(kodeMelli), AdviceDto.class);
        return adviceDto;
    }

}
