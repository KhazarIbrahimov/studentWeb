package com.studentweb.studentService;

import com.studentweb.studentEntitiy.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);
}
