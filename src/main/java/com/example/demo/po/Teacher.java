package com.example.demo.po;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int tId;//老师id
    private String jobNumber;//教师工号
    private String introduction;//介绍
    private String name;//姓名
    private String phone;//联系方式
    private int sex;//性别 0：男，1：女
    private Date birth;//出生日期
    private String password;//密码
    //一个老师教多门课，一门课被多个老师教
    @OneToMany(fetch = FetchType.LAZY,targetEntity = CourseClass.class,mappedBy = "teacher",cascade={CascadeType.PERSIST})
    private List<CourseClass> courseClasses;
//    @ManyToMany
//    @JoinTable(name = "user_authority",joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id"))
//    //1、关系维护端，负责多对多关系的绑定和解除
//    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
//    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Authority)
//    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
//    //即表名为user_authority
//    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
//    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
//    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
//    private List<Authority> authorityList;

    public int gettId() {
        return tId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseClasses(List<CourseClass> courseClasses) {
        this.courseClasses = courseClasses;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
