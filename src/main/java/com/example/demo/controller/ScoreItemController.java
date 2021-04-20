package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;

import com.example.demo.Data.ScoreItemData;
import com.example.demo.service.CourseClassService;
import com.example.demo.service.DemoDataListener;
import com.example.demo.service.ScoreItemService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;

@Controller
public class ScoreItemController {
    @Autowired
    private ScoreItemService scoreItemService;
    @Autowired
    private CourseClassService courseClassService;
    //文件导入学生本次实验成绩
    @RequestMapping(value = "/teacher/addStudentScore")
    public ModelAndView addStudentScore(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/addStudentScore.html");
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        return modelAndView;
    }
    @RequestMapping(value = "/teacher/addStudentScoreItem",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadScoreItem(@RequestPart(value = "file")MultipartFile file, @RequestParam(value = "ccNumber") String ccNumber,
                                  @RequestParam(value = "date") String date, @RequestParam(value = "experienceName") String experienceName,
                                  RedirectAttributes attributes) throws IOException, ParseException {
        System.out.println(file.getOriginalFilename()+","+ccNumber+","+date+","+experienceName);
       // SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ", Locale.ENGLISH);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = fmt.parse(date);
       // System.out.println(file.);
        EasyExcel.read(file.getInputStream(), ScoreItemData.class,new DemoDataListener(scoreItemService,ccNumber,date1,experienceName)).sheet().headRowNumber(2).doRead();
        attributes.addAttribute("ccNumber",ccNumber);
        attributes.addAttribute("experienceName",experienceName);
        return "redirect:/teacher/findStudentScoreItemByName";
    }
    //获取某次实验的学生成绩
    @RequestMapping(value = "/teacher/findStudentScoreItemByName")
    public ModelAndView findScoreByName(@RequestParam("ccNumber")String ccNumber, @RequestParam("experienceName")String experienceName){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showScoreByLabName.html");
        modelAndView.addObject("title",ccNumber+"  "+experienceName);
        modelAndView.addObject("scoreItems",scoreItemService.findScoreItemsByCcNumberAndExperienceName(ccNumber,experienceName));
        return modelAndView;
    }
    //获取某班的所有实验成绩;搜索后
    @RequestMapping(value = "/teacher/showScoreItemByCcNumber")
    public ModelAndView findScoreItemsByCcNumberGroup(@RequestParam("ccId")int ccId){
        System.out.println(ccId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showScoreByCourseClass.html");
        modelAndView.addObject("allScoreItems",scoreItemService.findScoreItemsByCcIdGroup(ccId));
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId(1));
        return modelAndView;

    }
    //获取某班的所有实验成绩
    @RequestMapping(value = "/teacher/showScoreItemBySearch")
    public ModelAndView findScoreItemsByCcNumber(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showScoreByCourseClass.html");
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        return modelAndView;

    }
}