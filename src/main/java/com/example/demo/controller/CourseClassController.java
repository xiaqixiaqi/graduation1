package com.example.demo.controller;

import com.example.demo.Data.MessageData;
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
    //管理员添加课程班级页面
    @RequestMapping(value = "/teacher/addCourseClass")
    public ModelAndView addCourseClass(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/addCourseClass.html");
        modelAndView.addObject("courses",courseService.findAllCourse());
        return modelAndView;
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
        ModelAndView modelAndView = getModelAndView(session);
        modelAndView.setViewName("/teacher/addStudentScoreManual.html");
        return modelAndView;

    }
    //老师手动添加学生实验成绩（选择班级后）
    @RequestMapping(value = "/teacher/addScoreManualByClass")
    public ModelAndView addScoreManualByClass(@RequestParam("ccId")int ccId,HttpSession session){
        ModelAndView modelAndView = getModelAndView(session);
        modelAndView.setViewName("/teacher/addStudentScoreManual.html");
        modelAndView.addObject("scores",courseClassService.findScoresByCourseClass(ccId));
        return modelAndView;
    }
    //老师手动添加学生实验成绩（选择班级后）
    @RequestMapping(value = "/teacher/addingScoreManualByClass")
    public String addingScoreManualByClass(@RequestParam("experimentName")String experimentName, @RequestParam("ccId")String ccId,@RequestParam("date")String date, @RequestParam("scoreId")int[] scoreId,
                                           @RequestParam("previewScore")float[] previewScore, @RequestParam("operatingScore")float[] operatingScore,
                                           @RequestParam("reportScore")float[] reportScore,@RequestParam("remark")String[] remark, RedirectAttributes attributes) throws ParseException {
        scoreItemService.addScoreItem( experimentName, date,scoreId, previewScore,operatingScore, reportScore,remark) ;
        attributes.addAttribute("experienceName",experimentName);
        attributes.addAttribute("ccId",ccId);
        //存储是否存入数据库的信息
        MessageData messageData=new MessageData();
        messageData.setMsg("存入成功");
        attributes.addAttribute("message",messageData.getMsg());
        return "redirect:/teacher/findStudentScoreItemByName";
    }
    //查看某课程班级信息
    @RequestMapping(value = "/teacher/showCourseClassInfo")
    public ModelAndView showCourseClass(HttpSession session){
        ModelAndView modelAndView = getModelAndView(session);
        return modelAndView;
    }
    //查询某课程班级信息（搜索后）
    @RequestMapping(value = "/teacher/showCourseClassByResearch")
    public ModelAndView showCourseClassByResearch(@RequestParam("ccId")int ccId,HttpSession session){
        ModelAndView modelAndView = getModelAndView(session);
        modelAndView.addObject("courseClass",courseClassService.findCourseClassByCcId(ccId));
        return modelAndView;
    }

    private ModelAndView getModelAndView(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        int tId = (int) session.getAttribute("tId");
        if (tId == 1) {
            modelAndView.addObject("courseClasses", courseClassService.findAllCourseClass());
        } else {
            modelAndView.addObject("courseClasses", courseClassService.findAllCourseClassByTId(tId));
        }
        modelAndView.setViewName("/teacher/showResearchStudentByClass.html");
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
