package com.studentweb.controller;

import com.studentweb.studentEntitiy.Student;
import com.studentweb.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<Student>(student,HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Void> saveStudent(@RequestBody Student student, UriComponentsBuilder builder){
        studentService.saveStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/students/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(student.getId());
        existingStudent.setName(student.getName());
        existingStudent.setSurName(student.getSurName());

        studentService.updateStudent(existingStudent);
        return new ResponseEntity<Student>(existingStudent,HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
