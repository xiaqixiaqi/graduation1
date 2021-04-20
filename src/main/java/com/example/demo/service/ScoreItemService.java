package com.example.demo.service;

import com.example.demo.Data.ScoreItemData;
import com.example.demo.dao.*;
import com.example.demo.po.Score;
import com.example.demo.po.ScoreItem;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScoreItemService {
    @Resource
    private ScoreItemRepository scoreItemRepository;
    @Resource
    private ScoreRepository scoreRepository;
    @Resource
    private CourseClassRepository courseClassRepository;
    @Resource
    private StudentRepository studentRepository;
    //ccNumber课程班级编号
    public boolean saveScoreItems(List<ScoreItemData> scoreItemData, String ccNumber, Date date,String experienceName){
        for (ScoreItemData scoreData:scoreItemData) {
            Score score=scoreRepository.findScoreByStudentIDAndCcNumber(scoreData.getStudentId(),ccNumber);
            if (score==null){
                score=new Score();
                score.setCourseClass(courseClassRepository.findCourseClassByCcNumber(ccNumber));
                score.setStudent(studentRepository.findStudentByStudentID(scoreData.getStudentId()));
            }
            ScoreItem scoreItem=new ScoreItem();
            scoreItem.setDate(date);
            scoreItem.setOperatingScore(scoreData.getOperatingScore());
            scoreItem.setExperimentName(experienceName);
            scoreItem.setPreviewScore(scoreData.getPreviewScore());
            scoreItem.setReportScore(scoreData.getReportScore());
            //记录这次实验的总成绩
            scoreItem.setTotalScore((float) (scoreData.getOperatingScore()*0.3+scoreData.getPreviewScore()*0.3+scoreData.getReportScore()*0.4));
            scoreItem.setScore(score);
            scoreItemRepository.save(scoreItem);
            if (score.getScoreItems()== null){
                List<ScoreItem> items = new ArrayList<>();
                items.add(scoreItem);
                score.setScoreItems(items);
            }else {
                score.getScoreItems().add(scoreItem);
            }
            scoreRepository.save(score);
            //重新保存该学生的总成绩
            score=scoreRepository.findById(score.getScoreId()).get();
            List<ScoreItem> scoreItems=score.getScoreItems();
            int count=scoreItems.size();
            float pTotal=0.0f,oTotal=0.0f,rTotal=0.0f;
            for(ScoreItem scoreItem1:scoreItems){
                pTotal+=scoreItem1.getPreviewScore();
                oTotal+=scoreItem1.getOperatingScore();
                rTotal+=scoreItem1.getReportScore();
            }
            score.setScoreValue((float) ((pTotal*0.3+oTotal*0.3+rTotal*0.4)/count));
            scoreRepository.save(score);

        }
        return true;
    }
    //教师查询某次学生实验成绩
    public List<ScoreItem> findScoreItemsByCcNumberAndExperienceName(String ccNumber,String experienceName){
        return scoreItemRepository.findScoreItemsByCcNumberAndExperienceName(ccNumber,experienceName);
    }
    public boolean addScoreItem(String experimentName,String date,int[] scoreId,float[] previewScore,float[] operatingScore,float[] reportScore) throws ParseException {
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = fmt.parse(date);
        //遍历这门课这个实验每个学生的成绩
        for (int i=0;i<scoreId.length;i++) {
            Score score=scoreRepository.findById(scoreId[i]).get();//找到这门课这个学生的成绩
            ScoreItem scoreItem=new ScoreItem();//新建一个学生的一次实验
            scoreItem.setScore(score);
            scoreItem.setReportScore(reportScore[i]);
            scoreItem.setPreviewScore(previewScore[i]);
            scoreItem.setExperimentName(experimentName);
            scoreItem.setOperatingScore(operatingScore[i]);
            //记录这次实验的总成绩
            float totalScore= (float) (reportScore[i]*0.4+previewScore[i]*0.3+operatingScore[i]*0.3);
            scoreItem.setTotalScore(totalScore);
            scoreItem.setDate(date1);
            //重新保存该学生的总成绩
            List<ScoreItem> scoreItems=score.getScoreItems();
            if (scoreItems!=null) {
                int count = scoreItems.size() + 1;
                float pTotal = previewScore[i], oTotal = operatingScore[i], rTotal = reportScore[i];
                for (ScoreItem scoreItem1 : scoreItems) {
                    pTotal =pTotal+scoreItem1.getPreviewScore();
                    oTotal =oTotal+ scoreItem1.getOperatingScore();
                    rTotal = rTotal+scoreItem1.getReportScore();
                }
                score.setScoreValue((float) ((pTotal * 0.3 + oTotal * 0.3 + rTotal * 0.4) / count));
                scoreRepository.save(score);
            }
            scoreItemRepository.save(scoreItem);


        }

        return true;
    }
    public Map findScoreItemsByCcIdGroup(int ccId){
        Map<String,List<ScoreItem>> scoreMap=new HashMap();
        List<String> experimentName=scoreItemRepository.findExperimentNameByCcNumberGroup(ccId);
        for (String name:experimentName) {
            List<ScoreItem> scoreItems=scoreItemRepository.findScoreItemsByCcNumberAndExperimentName(ccId,name);
            scoreMap.put(name,scoreItems);
        }
        return scoreMap;
    }

}
