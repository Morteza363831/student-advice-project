package com.example.studentadviceproject.repository;

import com.example.studentadviceproject.dto.StudentDto;
import com.example.studentadviceproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByAdviceId(long adviceId);

    @Query(value = "select * from student where kode_melli = :kodeMelli", nativeQuery = true)
    Student findStudentsByKodeMelli(@Param("kodeMelli") String kodeMelli);

    void deleteStudentByKodeMelli(String kodeMelli);
}
