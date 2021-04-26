package com.example.demo.dao;

import com.example.demo.po.Score;
import com.example.demo.po.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score,Integer> {
    @Query("select s from Score s where s.student.studentID=?1 and s.courseClass.ccId=?2")
    Score findScoreByStudentIDAndCcNumber(String studentId, int ccId);

}
