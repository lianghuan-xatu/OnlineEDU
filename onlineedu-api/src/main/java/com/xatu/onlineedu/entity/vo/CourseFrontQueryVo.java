package com.xatu.onlineedu.entity.vo;

public class CourseFrontQueryVo {

        private String title;
        private String teacherId;
        private String subjectParentId;
        private String subjectId;
        private String buyCountSort;
        private String gmtCreateSort;
        private String priceSort;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getBuyCountSort() {
        return buyCountSort;
    }

    public void setBuyCountSort(String buyCountSort) {
        this.buyCountSort = buyCountSort;
    }

    public String getGmtCreateSort() {
        return gmtCreateSort;
    }

    public void setGmtCreateSort(String gmtCreateSort) {
        this.gmtCreateSort = gmtCreateSort;
    }

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }

    @Override
    public String toString() {
        return "CourseFrontQueryVo{" +
                "title='" + title + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", subjectParentId='" + subjectParentId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", buyCountSort='" + buyCountSort + '\'' +
                ", gmtCreateSort='" + gmtCreateSort + '\'' +
                ", priceSort='" + priceSort + '\'' +
                '}';
    }
}
