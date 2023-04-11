package com.studentweb.studentService;

import com.studentweb.studentEntitiy.Student;
import com.studentweb.studentRepository.StudentRepository;

import java.util.List;

public interface StudentService {

    StudentRepository studentRepository = null;


    List<Student> getAllStudents();
    Student getStudentById(int id);
    Student addStudent(Student student);
    Student updateStudent(Student updatedStudent);
    boolean deleteStudent(Long id);

    default void saveStudent(Student student){
        studentRepository.save(student);
    };

    default void deleteStudentById(Long id){
        studentRepository.deleteById(Math.toIntExact(id));
    };
}
