package com.example.demo.service;

import com.example.demo.dao.CourseClassRepository;
import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.po.Course;
import com.example.demo.po.CourseClass;
import com.example.demo.po.Score;
import com.example.demo.po.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseClassService {
    @Resource
    private CourseRepository courseRepository;
    @Resource
    private CourseClassRepository courseClassRepository;
    @Resource
    private StudentRepository studentRepository;
    public CourseClass addCourseClass(String ccNumber, Course course, String classTime, String semester, Teacher teacher){
        CourseClass courseClass=new CourseClass();
        courseClass.setCcNumber(ccNumber);
        courseClass.setClassTime(classTime);
        courseClass.setCourse(course);
        courseClass.setSemester(semester);
        courseClass.setTeacher(teacher);
        courseClass= courseClassRepository.save(courseClass);
        return courseClass;
    }
    public List<CourseClass> findAllCourseClassByTId(int tId){
        return courseClassRepository.findCoursesClassByTId(tId);
    }
    public List<Score> findScoresByCourseClass(int ccId){
        return courseClassRepository.findById(ccId).get().getScores();

    }
    public List<CourseClass> findAllCourseClass(){
        return (List<CourseClass>) courseClassRepository.findAll();

    }
    //通过课程id查询该老师的课程信息
    public CourseClass findCourseClassByCcId(int ccId){
        return courseClassRepository.findById(ccId).get();
    }
    //学生端查询课程班级信息
    public List<Score> findAllCourseClass(int sId){
        return studentRepository.findById(sId).get().getScores();
    }
}
