package com.example.demo.dao;

import com.example.demo.po.CourseClass;
import com.example.demo.po.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    @Query("select s from Student s where s.studentID=?1")
    Student findStudentByStudentID(String studentID);


}
