package com.studentweb.studentRepository;

import com.studentweb.studentEntitiy.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
