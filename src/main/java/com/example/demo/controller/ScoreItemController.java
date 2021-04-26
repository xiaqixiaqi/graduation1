package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;

import com.example.demo.Data.MessageData;
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
        int tId=(Integer) session.getAttribute("tId");
        if (tId==1){
            modelAndView.addObject("courseClasses",courseClassService.findAllCourseClass());
       }
        else
            modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId(tId));
        return modelAndView;
    }
    @RequestMapping(value = "/teacher/addStudentScoreItem",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadScoreItem(@RequestPart(value = "file")MultipartFile file, @RequestParam(value = "ccId") int ccId,
                                  @RequestParam(value = "date") String date, @RequestParam(value = "experienceName") String experienceName,
                                  RedirectAttributes attributes) throws IOException, ParseException {
        System.out.println(file.getOriginalFilename()+","+ccId+","+date+","+experienceName);
       // SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ", Locale.ENGLISH);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = fmt.parse(date);
        //存储是否存入数据库的信息
        MessageData messageData=new MessageData();
        messageData.setMsg("");
        EasyExcel.read(file.getInputStream(), ScoreItemData.class,new DemoDataListener(scoreItemService,ccId,date1,experienceName,messageData)).sheet().headRowNumber(2).doRead();
        attributes.addAttribute("ccId",ccId);
        attributes.addAttribute("experienceName",experienceName);
        attributes.addAttribute("message",messageData.getMsg());
        System.out.println("返回的数据："+messageData.getMsg());
        return "redirect:/teacher/findStudentScoreItemByName";
    }
    //获取某次实验的学生成绩
    @RequestMapping(value = "/teacher/findStudentScoreItemByName")
    public ModelAndView findScoreByName(@RequestParam("ccId")int ccId, @RequestParam("experienceName")String experienceName,@RequestParam("message")String message){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showScoreByLabName.html");
        modelAndView.addObject("title",ccId+"  "+experienceName);
        modelAndView.addObject("scoreItems",scoreItemService.findScoreItemsByCcIdAndExperienceName(ccId,experienceName));
        System.out.println("数据"+scoreItemService.findScoreItemsByCcIdAndExperienceName(ccId,experienceName));
        modelAndView.addObject("message",message);
        return modelAndView;
    }
    //获取某班的所有实验成绩;搜索后
    @RequestMapping(value = "/teacher/showScoreItemByCcNumber")
    public ModelAndView findScoreItemsByCcNumberGroup(@RequestParam("ccId")int ccId,HttpSession session){
        System.out.println(ccId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showScoreByCourseClass.html");
        modelAndView.addObject("allScoreItems",scoreItemService.findScoreItemsByCcIdGroup(ccId));
        if ((Integer) session.getAttribute("tId")==1){
            modelAndView.addObject("courseClasses",courseClassService.findAllCourseClass());
        }
        else{
            modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId((Integer) session.getAttribute("tId")));
        }
        return modelAndView;

    }
    //获取某班的所有实验成绩
    @RequestMapping(value = "/teacher/showScoreItemBySearch")
    public ModelAndView findScoreItemsByCcNumber(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showScoreByCourseClass.html");
        int tId=(Integer) session.getAttribute("tId");
        if (tId==1){
            modelAndView.addObject("courseClasses",courseClassService.findAllCourseClass());
        }
        else
        modelAndView.addObject("courseClasses",courseClassService.findAllCourseClassByTId(tId));
        return modelAndView;

    }
}
