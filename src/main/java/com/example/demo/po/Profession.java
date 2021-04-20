package com.example.demo.po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int pId;//专业id
    private String professionID;//专业编号
    private String content;//专业描述
    private String pName;//专业名称
    //一个专业有多个学生
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Student.class,mappedBy = "profession",cascade={CascadeType.PERSIST})
    private List<Student> students;

    public int getpId() {
        return pId;
    }

    public String getProfessionID() {
        return professionID;
    }

    public void setProfessionID(String professionID) {
        this.professionID = professionID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
