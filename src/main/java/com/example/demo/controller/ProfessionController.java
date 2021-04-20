package com.example.demo.controller;

import com.example.demo.service.ProfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
public class ProfessionController {
    @Resource
    private ProfessionService professionService;
    @RequestMapping(value = "/teacher/addProfession")
    public String addProfession(){
        return "/teacher/addProfession.html";
    }
    @RequestMapping(value = "/teacher/addingProfession")
    public String addingProfession(@RequestParam("professionID")String professionID , @RequestParam("pName")String pName,
                                   @RequestParam("content")String content, RedirectAttributes attributes){
        professionService.addProfession(professionID,pName,content);
        return "redirect:/teacher/showAllProfession";
    }
    @RequestMapping(value = "/teacher/showAllProfession")
    public ModelAndView showAllProfession(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/teacher/showAllProfession.html");
        modelAndView.addObject("professions",professionService.showAllProfession());
        return modelAndView;
    }
}
