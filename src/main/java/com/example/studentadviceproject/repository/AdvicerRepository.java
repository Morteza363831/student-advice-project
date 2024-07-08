package com.example.studentadviceproject.repository;

import com.example.studentadviceproject.entity.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvicerRepository extends JpaRepository<Advice,Long> {

}
