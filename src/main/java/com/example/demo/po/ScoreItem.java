package com.example.demo.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "scoreItem")
public class ScoreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id自动递增
    private int scoreItemId;//实验id
    private Date date;//时间
    private String experimentName;//实验名称
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Score.class,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "scoreId",referencedColumnName = "scoreId")
    private Score score;//成绩id
    private int operate;//0：未录入  1：已录入    2：已删除
    private float previewScore;//预习成绩
    private float operatingScore;//操作成绩
    private float reportScore;//实验报告成绩;
    private float totalScore;//总成绩
    private String remark;//备注


    public int getScoreItemId() {
        return scoreItemId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public float getPreviewScore() {
        return previewScore;
    }

    public void setPreviewScore(float previewScore) {
        this.previewScore = previewScore;
    }

    public float getOperatingScore() {
        return operatingScore;
    }

    public void setOperatingScore(float operatingScore) {
        this.operatingScore = operatingScore;
    }

    public float getReportScore() {
        return reportScore;
    }

    public void setReportScore(float reportScore) {
        this.reportScore = reportScore;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
