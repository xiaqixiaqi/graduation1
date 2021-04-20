package com.example.demo.controller;

import com.example.demo.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class CourseController {
    @Resource
    private CourseService courseService;
    @RequestMapping(value = "/teacher/addCourse")
    public String addCourse(){
        return "/teacher/addCourse.html";
    }
    @RequestMapping(value = "/teacher/addingCourse")
    public String addingCourse(@RequestParam("courseCode")String courseCode,@RequestParam("courseName")String courseName,
                               @RequestParam("content")String content,@RequestParam("credits")int credits){
        courseService.addCourse(courseCode,courseName,content,credits);
        return "redirect:/teacher/addCourse";
    }
}
