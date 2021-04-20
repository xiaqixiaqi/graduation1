package com.example.demo.po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
//所有课程
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int courseId;//课程id
    private String courseCode;//课程代码
    private String courseName;//课程名称
    private String content;//课程内容
    private int credits;//学分
    //一个课程有多个课程班级
    @OneToMany(fetch = FetchType.LAZY,targetEntity = CourseClass.class,mappedBy = "course",cascade={CascadeType.PERSIST})
    private List<CourseClass> courseClasses;

    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseClasses(List<CourseClass> courseClasses) {
        this.courseClasses = courseClasses;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
