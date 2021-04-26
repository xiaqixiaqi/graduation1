package com.example.demo.dao;

import com.example.demo.po.Score;
import com.example.demo.po.ScoreItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScoreItemRepository extends CrudRepository<ScoreItem,Integer> {
    @Query("select s from ScoreItem s where s.score.courseClass.ccId=?1 and s.experimentName=?2")
    List<ScoreItem> findScoreItemsByCcIdAndExperienceName(int ccId, String experienceName);
    @Query("select s from ScoreItem s where s.score.courseClass.ccId=?1 and s.experimentName=?2")
    List findScoreItemsByCcIdAndExperimentName(int ccId,String experimentName);
    @Query("select s.experimentName from ScoreItem s where s.score.courseClass.ccId=?1 group by s.experimentName")
    List findExperimentNameByCcNumberGroup(int ccId);
}
