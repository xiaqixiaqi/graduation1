package com.example.demo.dao;

import com.example.demo.po.Course;
import com.example.demo.po.Teacher;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Integer> {
    @Query("select s from Teacher s where s.jobNumber=?1")
    Teacher findTeacherByJobNumber(String jobNumber);

}
