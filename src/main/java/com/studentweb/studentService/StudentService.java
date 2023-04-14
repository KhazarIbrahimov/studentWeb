package com.studentweb.studentService;

import com.studentweb.studentEntitiy.Student;
import com.studentweb.studentRepository.StudentRepository;

import java.util.List;

public interface StudentService {

    StudentRepository studentRepository = null;


    List<Student> getAllStudents();
    Student getStudentById(long id) throws Exception;
    Student addStudent(Student student);
    Student updateStudent(Student updatedStudent);

    void updateStudent(Long id, Student student) throws Exception;

    boolean deleteStudent(Long id) throws Exception;

    default void saveStudent(Student student){
        studentRepository.save(student);
    };

    default void deleteStudentById(Long id){
        studentRepository.deleteById(Math.toIntExact(id));
    };

    default Student getStudentById(Long id) throws Exception {
        return studentRepository.findById(Math.toIntExact(id)).get();
    };



}
