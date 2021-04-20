package com.example.demo.Data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ScoreItemData {
    @ExcelProperty("序号")
    private String index;
    @ExcelProperty("学号")
    private String studentId;
    @ExcelProperty("姓名")
    private String studentName;
    @ExcelProperty("课前预习成绩")
    private float previewScore;//预习成绩
    @ExcelProperty("实验操作成绩")
    private float operatingScore;//操作成绩
    @ExcelProperty("实验报告成绩")
    private float reportScore;//实验报告成绩;
    @ExcelProperty("备注")
    private String tip;//实验报告成绩;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
