package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.po.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseService {
    @Resource
    private CourseRepository courseRepository;
    public boolean addCourse(String courseCode,String courseName,String content,int credits){
        Course course=new Course();
        course.setContent(content);
        course.setCredits(credits);
        course.setCourseCode(courseCode);
        course.setCourseName(courseName);
        courseRepository.save(course);
        return true;
    }
    public Course findCourseById(int courseId ){
        return courseRepository.findById(courseId).get();
    }
    public Course findCourseByCourseCode(String courseCode){
        return courseRepository.findCourseByCourseCode(courseCode);
    }
}
