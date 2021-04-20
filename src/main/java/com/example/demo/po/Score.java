package com.example.demo.po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int scoreId;//成绩id
    private float scoreValue;//分数
    private String note;//备注
    //一个学生有多门课程成绩
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Student.class,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "sId",referencedColumnName = "sId")
    private Student student;//学生id
    //一个课程班级有多个学生有该门课程成绩
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = CourseClass.class,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "ccId",referencedColumnName = "ccId")
    private CourseClass courseClass;//课程班级id
    //一个成绩有多个成绩项组成
    @OneToMany(fetch = FetchType.LAZY,targetEntity = ScoreItem.class,mappedBy = "score",cascade={CascadeType.PERSIST})
    private List<ScoreItem> scoreItems;


    public int getScoreId() {
        return scoreId;
    }

    public float getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(float scoreValue) {
        this.scoreValue = scoreValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseClass getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(CourseClass courseClass) {
        this.courseClass = courseClass;
    }

    public List<ScoreItem> getScoreItems() {
        return scoreItems;
    }

    public void setScoreItems(List<ScoreItem> scoreItems) {
        this.scoreItems = scoreItems;
    }


}
