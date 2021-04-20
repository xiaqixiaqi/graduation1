package com.example.demo.po;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int sId;//学生id
    private String name;//姓名
    private String studentID;//学号
    private String phone;//联系方式
    private int sex;//性别 0：男，1：女
    private Date birth;//出生日期
    private String password;//密码
    private String nativePlace;//出生地
    //一个学生有一个专业，多个学生属于一个专业
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Profession.class,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "pId",referencedColumnName = "pId")
    private Profession profession;//专业id
    //一个学生有多门课程成绩
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Score.class,mappedBy = "student",cascade={CascadeType.PERSIST})
    private List<Score> scores;
    public int getsId() {
        return sId;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
