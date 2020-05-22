package com.xatu.onlineedu.entity.excelEntity;

import com.alibaba.excel.annotation.ExcelProperty;

public class SubjectData
{
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

    public String getOneSubjectName() {
        return oneSubjectName;
    }

    public void setOneSubjectName(String oneSubjectName) {
        this.oneSubjectName = oneSubjectName;
    }

    public String getTwoSubjectName() {
        return twoSubjectName;
    }

    public void setTwoSubjectName(String twoSubjectName) {
        this.twoSubjectName = twoSubjectName;
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                "oneSubjectName='" + oneSubjectName + '\'' +
                ", twoSubjectName='" + twoSubjectName + '\'' +
                '}';
    }
}
