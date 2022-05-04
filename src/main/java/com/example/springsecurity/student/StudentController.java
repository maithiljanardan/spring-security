package com.example.springsecurity.student;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

  private static final List<Student> STUDENTS = List.of(new Student(1, "John Doe")
      , new Student(2, "John Smith"), new Student(3, "Maria Jones"));

  @RequestMapping("/{studentId}")
  public Student getStudent(@PathVariable("studentId") Integer studentId) throws Exception {
    return STUDENTS.stream()
        .filter(student -> student.getStudentId().equals(studentId))
        .findFirst()
        .orElseThrow(() -> new Exception("Student Not Found!"));
  }


}
