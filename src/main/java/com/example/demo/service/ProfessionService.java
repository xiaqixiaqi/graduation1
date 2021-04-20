package com.example.demo.service;

import com.example.demo.dao.ProfessionRepository;
import com.example.demo.po.Profession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProfessionService {
    @Resource
    private ProfessionRepository professionRepository;
    public boolean addProfession(String professionID ,String pName,String content){
        Profession profession=new Profession();
        profession.setProfessionID(professionID);
        profession.setContent(content);
        profession.setpName(pName);
        professionRepository.save(profession);
        return true;
    }
    public List<Profession> showAllProfession(){
        return (List<Profession>) professionRepository.findAll();
    }
}
