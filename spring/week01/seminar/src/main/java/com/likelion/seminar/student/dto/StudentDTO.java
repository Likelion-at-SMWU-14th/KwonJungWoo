package com.likelion.seminar.student.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String studentId;

    private String name;

    private LocalDate dateOfBirth;
}
