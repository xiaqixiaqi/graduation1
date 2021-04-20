package com.example.demo.po;

import javax.persistence.*;
import java.util.List;

//课程班级信息
@Entity
@Table(name = "courseClass")
public class CourseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int ccId;//课程班级id
    private String ccNumber;//课程班级编号
    private String semester;//学期
    //一门课程属于多个课程班级，一个课程有多个课程班级
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Course.class,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "courseId",referencedColumnName = "courseId")
    private Course course;//课程id
    //一个课程班级有一个老师教，一个老师教多门课
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Teacher.class,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "tId",referencedColumnName = "tId")
    private Teacher teacher;//教师id
    private String classTime;//上课时间
    //一个课程班级有多个学生的成绩
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Score.class,mappedBy = "courseClass",cascade={CascadeType.PERSIST})
    private List<Score> scores;

    public int getCcId() {
        return ccId;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
