package com.example.demo.service;

import com.example.demo.dao.ProfessionRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.po.Score;
import com.example.demo.po.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private ProfessionRepository professionRepository;
    public boolean addStudent(String studentID,int pId,String name,int sex,String phone,String birth,String nativePlace) throws ParseException {
        Student student=new Student();
        student.setStudentID(studentID);
        student.setProfession(professionRepository.findById(pId).get());
        student.setSex(sex);
        student.setName(name);
        student.setPhone(phone);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(birth);
        student.setBirth(date);
        student.setNativePlace(nativePlace);
        studentRepository.save(student);
        return true;
    }
    //返回学生的所有成绩信息
    public List<Score> findAllScoreByStudent(int sId){
        return studentRepository.findById(sId).get().getScores();
    }
    //校验登录是否正确,account学号
    public Student findStudentByStudentID(String account){
       return studentRepository.findStudentByStudentID(account);
    }
    //修改学生信息
    public boolean updateStudent(String studentID,String name,int sex,String phone,String date,String nativePlace) throws ParseException {
        Student student=studentRepository.findStudentByStudentID(studentID);
        student.setPhone(phone);
        student.setNativePlace(nativePlace);
        if (!date.equals("")&&date!=null){
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            Date birth = fmt.parse(date);
            student.setBirth(birth);
        }
        student.setSex(sex);
        student.setName(name);
        studentRepository.save(student);
        return true;
    }
    //返回所有的学生
    public List<Student> findAllStudent(){
        return (List<Student>) studentRepository.findAll();
    }
    //修改学生密码
    public boolean updateStudentPassword(String studentID,String password){
        Student student=studentRepository.findStudentByStudentID(studentID);
        student.setPassword(password);
        studentRepository.save(student);
        return true;
    }
    //通过学生id返回学生
    public Student findStudentById(int sId){
        return studentRepository.findById(sId).get();
    }
}
