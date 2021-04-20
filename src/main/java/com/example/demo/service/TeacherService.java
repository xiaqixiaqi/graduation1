package com.example.demo.service;

import com.example.demo.dao.TeacherRepository;
import com.example.demo.po.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TeacherService {
    @Resource
    private TeacherRepository teacherRepository;
    public boolean addTeacher(String jobNumber,String name,int sex,String birth,String phone,String introduction) throws ParseException {
        Teacher teacher=new Teacher();
        teacher.setJobNumber(jobNumber);
        teacher.setName(name);
        teacher.setSex(sex);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(birth);
        teacher.setBirth(date);
        teacher.setPhone(phone);
        teacher.setIntroduction(introduction);
        teacherRepository.save(teacher);
        return true;
    }
    public List<Teacher> findAllTeacher(){
        return (List<Teacher>) teacherRepository.findAll();
    }
    //通过教师id查询老师
    public Teacher findTeacherByJobNumber(String jobNumber){
        return teacherRepository.findTeacherByJobNumber(jobNumber);
    }


}
