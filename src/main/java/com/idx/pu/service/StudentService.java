package com.idx.pu.service;

import com.idx.pu.dto.MessageDto;
import com.idx.pu.dto.StudentDto;
import com.idx.pu.entity.Student;
import com.idx.pu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {

        Student student = new Student();

        student.setRegNo(studentDto.getRegNo());
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());

        studentRepository.save(student);

        return studentDto;
    }

    public List<StudentDto> listStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentListResponse = new ArrayList<>();

        students.forEach(student -> {
            StudentDto studentDto = new StudentDto(student.getRegNo(), student.getName(), student.getMobile());
            studentListResponse.add(studentDto);
        });
        return studentListResponse;
    }

    public List<StudentDto> listStudentsByDtoProjection() {
        return studentRepository.listStudentsByDtoProjection();
    }


    public StudentDto getStudentByRegNo(String regNo) {

        Optional<Student> studentOptional = studentRepository.findByRegNo(regNo);

        if(studentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found by given regNo");
        }

        Student student = studentOptional.get();

        StudentDto studentDto = new StudentDto(student.getRegNo(), student.getName(), student.getMobile());
        return studentDto;
    }

    @Transactional
    public StudentDto updateStudent(StudentDto studentDto) {

        Optional<Student> studentOptional = studentRepository.findByRegNo(studentDto.getRegNo());

        if(studentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found by given regNo");
        }

        Student student = studentOptional.get();
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());

        student = studentRepository.save(student);

        studentDto.setName(student.getName());
        studentDto.setMobile(studentDto.getMobile());
        return studentDto;
    }

    @Transactional
    public MessageDto deleteStudentByRegNo(String regNo) {
        studentRepository.deleteByRegNo(regNo);
        return new MessageDto("Student deleted successfully");
    }
}
