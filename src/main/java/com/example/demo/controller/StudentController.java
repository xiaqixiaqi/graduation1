package com.example.demo.controller;

import com.example.demo.dao.StudentRepository;
import com.example.demo.po.Student;
import com.example.demo.service.ProfessionService;
import com.example.demo.service.StudentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;
import sun.misc.Contended;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;

@Controller
public class StudentController {
    @Resource
    private StudentService studentService;
    @Resource
    private ProfessionService professionService;
    //添加学生
    @RequestMapping(value = "/teacher/addStudent")
    public ModelAndView addStudent(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/addStudent.html");
        modelAndView.addObject("professions",professionService.showAllProfession());
        return modelAndView;
    }
    //添加学生（后台数据处理）
    @RequestMapping(value = "/teacher/addingStudent")
    public String addingStudent(@RequestParam("studentID")String studentID,@RequestParam("pId")int pId,@RequestParam("name")String name,@RequestParam("phone")String phone,
                                @RequestParam("sex")int sex,@RequestParam("birth")String birth,@RequestParam("nativePlace")String nativePlace) throws ParseException {
        studentService.addStudent(studentID,pId,name,sex,phone,birth,nativePlace);
        return "redirect:/teacher/addStudent";
    }
    //学生查询学生成绩
    @RequestMapping(value = "/student/showResearchScore")
    public ModelAndView showResearchScore(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/student/showResearchScore.html");
        System.out.println("aaaaaaaa"+(Integer) session.getAttribute("sId"));
        modelAndView.addObject("scores",studentService.findAllScoreByStudent((Integer) session.getAttribute("sId")));
        return modelAndView;
    }
    //搜索学生信息
    @RequestMapping(value = "/teacher/researchStudent")
    public String researchStudentInfo(){
        return "/teacher/showResearchStudent.html";
    }
    //返回搜索学生信息
    @RequestMapping(value = "/teacher/researchedStudent")
    public ModelAndView researchedStudent(@RequestParam("studentID")String studentID){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showResearchStudent.html");
        modelAndView.addObject("student",studentService.findStudentByStudentID(studentID));
        return modelAndView;
    }
    //修改学生信息
    @RequestMapping(value = "/teacher/updatingStudent")
    public String updateStudent(@RequestParam("studentID")String studentID,@RequestParam("name")String name,
                                @RequestParam("sex")int sex,@RequestParam("phone")String phone,@RequestParam("date")String date,
                                @RequestParam("nativePlace")String nativePlace) throws ParseException {
        studentService.updateStudent(studentID,name,sex,phone,date,nativePlace);
        return "redirect:/teacher/researchStudent";
    }
    //显示所有学生
    @RequestMapping(value = "/teacher/showAllStudent")
    public ModelAndView showAllStudent(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showAllStudent.html");
        modelAndView.addObject("students",studentService.findAllStudent());
        return modelAndView;
    }
    //学生查看个人信息
//    @RequestMapping(value = "/showStudentInfo")
//    public ModelAndView showStudentInfo(HttpSession session){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("/student/updateStudentInfo.html");
//        modelAndView.addObject("student",studentService.findStudentByStudentID());
//        return
//    }
    //修改学生账号密码
    @RequestMapping(value = "/student/updateStudentPassword")
    public ModelAndView updateStudentPassword(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/student/updateStudentPassword.html");
        modelAndView.addObject("student",studentService.findStudentById((Integer) session.getAttribute("sId")));
        return modelAndView;
    }
    //学生修改学生账号密码
    @RequestMapping(value = "/student/updatingStudentPassword")
    public ModelAndView updatingStudentPassword(@RequestParam("studentID")String studentID,@RequestParam("oldPassword")String oldPassword,
                                                @RequestParam("newPassword")String newPassword,HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/student/updateStudentPassword.html");
        Student student=studentService.findStudentByStudentID(studentID);
        if (!student.getPassword().equals(oldPassword)){
            modelAndView.addObject("tip","原始密码错误");
        }else{
            studentService.updateStudentPassword(studentID,newPassword);
            modelAndView.addObject("tip","修改成功");
        }
        modelAndView.addObject("student",studentService.findStudentById((Integer) session.getAttribute("sId")));
        return modelAndView;
    }
}
