package com.example.demo.controller;

import com.example.demo.po.Student;
import com.example.demo.po.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;
    @RequestMapping(value = "/login")
    public String Login(){
        return "/student/login.html";
    }
    @RequestMapping(value = "/logining")
    public String logining(@RequestParam("account")String account, @RequestParam("password")String password,
                           @RequestParam("role")int role,HttpSession session){

        if (role==0){
            Student student=studentService.findStudentByStudentID(account);
            if (student!=null&&(password==student.getPassword()||student.getPassword().equals(password))){
                session.setAttribute("sId",student.getsId());
                return "redirect:/student/showResearchScore";
            }else {
                return "redirect:/login";
            }
        }else if (role==1){
            Teacher teacher=teacherService.findTeacherByJobNumber(account);
            if (teacher!=null&&(password==teacher.getPassword()||teacher.getPassword().equals(password))){
                session.setAttribute("tId",teacher.gettId());
                return "redirect:/teacher/addStudentScore";
            }else {
                return "redirect:/login";
            }

        }
        return "redirect:/login";
    }
    @RequestMapping(value = "remove")
    public String remove(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
    @RequestMapping(value = "/test")
    public String test(){
        return "/teacher/forms-validation.html";
    }
    @RequestMapping(value = "/test1")
    public String test1(){
        return "/student/showResearchScore.html";
    }

}
