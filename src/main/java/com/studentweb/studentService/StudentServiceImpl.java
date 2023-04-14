package com.studentweb.studentService;

import com.studentweb.studentEntitiy.Student;
import com.studentweb.studentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) throws Exception {
        return getStudentById((long) id);
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(Math.toIntExact(id));
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new Exception("bu id ile telebe tapilmadi :  " + id);
        }
    }

    @Override
    public Student addStudent(Student student) {

        return studentRepository.save(new Student());

    }

    @Override
    public Student updateStudent(Student updatedStudent) {
        return null;
    }

    @Override
    public void updateStudent(Long id, Student student) throws Exception {
        Optional<Student> existingStudent = studentRepository.findById(Math.toIntExact(id));
        if (existingStudent.isPresent()) {
            student.setId(Math.toIntExact(id));
            studentRepository.save(student);
        } else {
            throw new Exception("bu id ile telebe tapilmadi : " + id);
        }
    }

    @Override
    public boolean deleteStudent(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(Math.toIntExact(id));
        if (student.isPresent()) {
            studentRepository.deleteById(Math.toIntExact(id));
        } else {
            throw new Exception("bu id ile telebe tapilmadi : " + id);
        }
        return false;
    }
}

