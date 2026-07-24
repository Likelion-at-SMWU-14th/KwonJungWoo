package com.likelion.seminar.student.controller;

import com.likelion.seminar.student.dto.StudentDTO;
import com.likelion.seminar.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public void createStudent(@RequestBody StudentDTO studentDTO) {}

    @GetMapping
    public List<StudentDTO> getAllStudents() {}

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable("id") int id) {}

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {}

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id) {}

}
