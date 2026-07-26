package com.likelion.seminar.student.service;

import com.likelion.seminar.student.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final List<StudentDTO> studentDTOList;

    public void createStudent(StudentDTO studentDTO) { this.studentDTOList.add(studentDTO); }

    public List<StudentDTO> getStudents() { return this.studentDTOList; }

    public StudentDTO getStudentById(String studentId) {
        return studentDTOList.stream() // 학생 목록 차례대로 처리
                .filter(student ->
                        student.getStudentId().equals(studentId)) // 학번이 일치하는 학생 필터
                .findFirst() // 일치하는 첫번째 학생 찾기
                .orElse(null); // 일치하는 학생이 없으면 null 반환
    }

    public void updateStudent(String studentId, StudentDTO studentDTO) {
        StudentDTO targetStudent = getStudentById(studentId); // get 함수를 활용해 학생 찾기

        if(studentDTO.getStudentId() != null) {
            targetStudent.setStudentId(studentDTO.getStudentId());
        }

        if(studentDTO.getName() != null) {
            targetStudent.setName(studentDTO.getName());
        }

        if(studentDTO.getDateOfBirth() != null) {
            targetStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        }
    }

    public void deleteStudent(String studentId) {
        StudentDTO targetStudent = getStudentById(studentId); // get 함수를 활용해 학생 찾기
        studentDTOList.remove(targetStudent);
    }
}
