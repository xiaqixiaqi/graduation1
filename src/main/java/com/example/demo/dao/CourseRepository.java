package com.example.demo.dao;

import com.example.demo.po.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
    @Query("select s from Course s where s.courseCode=?1")
    Course findCourseByCourseCode(String courseCode);
}
