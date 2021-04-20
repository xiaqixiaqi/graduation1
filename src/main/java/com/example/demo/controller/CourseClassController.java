package com.example.demo.controller;

import com.example.demo.po.Course;
import com.example.demo.po.CourseClass;
import com.example.demo.po.Score;
import com.example.demo.po.Teacher;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class CourseClassController {
    @Resource
    private ScoreService scoreService;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseClassService courseClassService;
    @Resource
    private TeacherService teacherServic;
    @Resource
    private ScoreItemService scoreItemService;
    @RequestMapping(value = "/teacher/addCourseClass")
    public String addCourseClass(){
        return "/teacher/addCourseClass.html";
    }
    @RequestMapping(value = "/teacher/addingCourseClass")
    public String addingCourseClass(@RequestParam("ccNumber")String ccNumber,@RequestParam("jobNumber")String jobNumber, @RequestParam("courseCode")String courseCode,
                                    @RequestParam("classTime")String classTime,@RequestParam("students")String students,@RequestParam("semester")String semester){
        Course course=courseService.findCourseByCourseCode(courseCode);
        Teacher teacher=teacherServic.findTeacherByJobNumber(jobNumber);
        CourseClass courseClass=courseClassService.addCourseClass(ccNumber, course, classTime, semester,teacher);
        List<Score> scores=scoreService.addScoresByStudentsAndScore(students,courseClass);
        return "redirect:/teacher/addCourseClass";
    }
    //老师手动添加学生实验成绩
    @RequestMapping(value = "/teacher/addScoreManual")
    public ModelAndView addScoreManual(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/addStudentScoreManual.html");
        //需要修改，老师id，老师的所有课程班级
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        return modelAndView;
    }
    //老师手动添加学生实验成绩（选择班级后）
    @RequestMapping(value = "/teacher/addScoreManualByClass")
    public ModelAndView addScoreManualByClass(@RequestParam("ccId")int ccId,HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/addStudentScoreManual.html");
        //老师的所有课程班级
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        modelAndView.addObject("scores",courseClassService.findScoresByCourseClass(ccId));
        return modelAndView;
    }
    //老师手动添加学生实验成绩（选择班级后）
    @RequestMapping(value = "/teacher/addingScoreManualByClass")
    public String addingScoreManualByClass(@RequestParam("experimentName")String experimentName, @RequestParam("ccNumber")String ccNumber,@RequestParam("date")String date, @RequestParam("scoreId")int[] scoreId,
                                           @RequestParam("previewScore")float[] previewScore, @RequestParam("operatingScore")float[] operatingScore,
                                           @RequestParam("reportScore")float[] reportScore, RedirectAttributes attributes) throws ParseException {
        scoreItemService.addScoreItem( experimentName, date,scoreId, previewScore,operatingScore, reportScore) ;
        attributes.addAttribute("experienceName",experimentName);
        attributes.addAttribute("ccNumber",ccNumber);
        return "redirect:/teacher/findStudentScoreItemByName";
    }
    //查看某课程班级信息
    @RequestMapping(value = "/teacher/showCourseClassInfo")
    public ModelAndView showCourseClass(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showResearchStudentByClass.html");
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        return modelAndView;
    }
    //查询某课程班级信息（搜索后）
    @RequestMapping(value = "/teacher/showCourseClassByResearch")
    public ModelAndView showCourseClassByResearch(@RequestParam("ccId")int ccId,HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showResearchStudentByClass.html");
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        modelAndView.addObject("courseClass",courseClassService.findCourseClassByCcId(ccId));
        return modelAndView;
    }
    //学生端查询课程班级的信息
    @RequestMapping(value = "/student/showAllCourseClass")
    public ModelAndView researcheCourseClass(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/student/showCourseClass.html");
        modelAndView.addObject("scores",courseClassService.findAllCourseClass((Integer) session.getAttribute("sId")));
        return modelAndView;
    }
}
