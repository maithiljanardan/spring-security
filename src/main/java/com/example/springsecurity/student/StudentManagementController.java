package com.example.springsecurity.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

  private List<Student> STUDENTS = new ArrayList<>(List.of(new Student(1, "John Doe")
      , new Student(2, "John Smith"), new Student(3, "Maria Jones")));

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
  public List<Student> getAllStudents() {
    System.out.println("getAllStudents");
    return STUDENTS;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('student:write')")
  public void registerNewStudent(@RequestBody Student student) {
    System.out.println("registerNewStudent");
    STUDENTS.add(student);
    System.out.println(student);
  }

  @DeleteMapping(path = "{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void deleteStudent(@PathVariable("studentId") Integer studentId) {
    System.out.println("deleteStudent");
    STUDENTS.removeIf(student -> student.getStudentId().equals(studentId));
    System.out.println(studentId);
  }

  @PutMapping(path = "{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
    System.out.println("updateStudent");
    System.out.println(String.format("%s %s", studentId, student));
  }

}
