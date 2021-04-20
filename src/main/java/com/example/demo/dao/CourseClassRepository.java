package com.example.demo.dao;

import com.example.demo.po.Course;
import com.example.demo.po.CourseClass;
import com.example.demo.po.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseClassRepository extends CrudRepository<CourseClass,Integer> {
    @Query("select s from CourseClass s where s.ccNumber=?1")
    CourseClass findCourseClassByCcNumber(String ccNumber);
    @Query("select s from CourseClass s where s.teacher.tId=?1")
    List<CourseClass> findCoursesClassByTId(int tId);

}
