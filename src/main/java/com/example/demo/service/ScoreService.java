package com.example.demo.service;

import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.po.CourseClass;
import com.example.demo.po.Score;
import com.example.demo.po.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Service
public class ScoreService {
    @Resource
    private ScoreRepository scoreRepository;
    @Resource
    private StudentRepository studentRepository;

    public List<Score> addScoresByStudentsAndScore(String students, CourseClass courseClass) {
        List<Score> scores=new LinkedList<>();
        String[] strarray=students.split(";");
        for (int i = 0; i < strarray.length; i++){
            Student student=studentRepository.findStudentByStudentID(strarray[i]);
            Score score=new Score();
            score.setStudent(student);
            score.setCourseClass(courseClass);
            scoreRepository.save(score);
            scores.add(score);
           // System.out.println(strarray[i]);
        }
        return scores;
    }
}
