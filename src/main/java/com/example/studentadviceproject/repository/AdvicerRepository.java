package com.example.studentadviceproject.repository;

import com.example.studentadviceproject.dto.AdviceDto;
import com.example.studentadviceproject.entity.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvicerRepository extends JpaRepository<Advice,Long> {

    @Query(value = "select * from advice where kode_melli = :kodeMelli", nativeQuery = true)
    Advice findAdviceByKodeMelli(@Param("kodeMelli") String kodeMelli);

    void deleteAdviceByKodeMelli(String kodeMelli);
}
