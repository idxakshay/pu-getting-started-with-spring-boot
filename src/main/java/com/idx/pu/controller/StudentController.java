package com.idx.pu.controller;

import com.idx.pu.dto.MessageDto;
import com.idx.pu.dto.StudentDto;
import com.idx.pu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping("{regNo}")
    public StudentDto getStudentByRegNo(@PathVariable("regNo") String regNo) {
        return studentService.getStudentByRegNo(regNo);
    }

    @GetMapping
    public List<StudentDto> listStudents() {
        //return studentService.listStudents();
        return studentService.listStudentsByDtoProjection();
    }

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("{regNo}")
    public MessageDto deleteStudentByRegNo(@PathVariable("regNo") String regNo) {
        return studentService.deleteStudentByRegNo(regNo);
    }
}
