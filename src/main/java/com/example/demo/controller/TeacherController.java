package com.example.demo.controller;

import com.example.demo.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;

@Controller
public class TeacherController {
    @Resource
    TeacherService teacherService;
    @RequestMapping(value = "/teacher/addTeacher")
    public String addTeacher(){
        return "/teacher/addTeacher.html";
    }
    @RequestMapping(value = "/teacher/addingTeacher")
    public String addingTeacher(@RequestParam("jobNumber")String jobNumber,@RequestParam("name")String name, @RequestParam("sex")int sex,
                                @RequestParam("birth")String birth,@RequestParam("phone")String phone,@RequestParam("introduction")String introduction) throws ParseException {
        teacherService.addTeacher(jobNumber,name,sex,birth,phone,introduction);
        return "redirect:/teacher/showAllTeacher";
    }
    @RequestMapping(value = "/teacher/showAllTeacher")
    public ModelAndView showAllTeacher(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showAllTeacher.html");
        modelAndView.addObject("teachers",teacherService.findAllTeacher());
        return modelAndView;
    }
}
