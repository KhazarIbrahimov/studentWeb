package com.studentweb.controller;

import com.studentweb.exception.StudentNotFoundException;
import com.studentweb.studentEntitiy.Student;
import com.studentweb.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws StudentNotFoundException, Exception {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new StudentNotFoundException("bu id ile telebe tapilmadi : " + id);
        } else {
            return ResponseEntity.ok(student);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.created(URI.create("/students/" + createdStudent.getId())).body(createdStudent);
    }


    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) throws StudentNotFoundException {
        student.setId(Math.toIntExact(id));
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            throw new StudentNotFoundException("bu id ile telebe tapilmadi : " + id);
        } else {
            System.out.println("telebe melumatlari ugurla yenilendi.");
            return ResponseEntity.ok(updatedStudent);

        }
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) throws StudentNotFoundException, Exception {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            System.out.println("telebe ugurla silindi.");
            return ResponseEntity.noContent().build();
        } else {
            throw new StudentNotFoundException("bu id ile telebe tapilmadi :: " + id);
        }
    }
}
