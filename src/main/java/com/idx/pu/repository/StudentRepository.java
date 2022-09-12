package com.idx.pu.repository;

import com.idx.pu.dto.StudentDto;
import com.idx.pu.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRegNo(String regNo);

    @Query("SELECT NEW com.idx.pu.dto.StudentDto(s.regNo, s.name, s.mobile) FROM Student s")
    List<StudentDto> listStudentsByDtoProjection();

    void deleteByRegNo(String regNo);
}
