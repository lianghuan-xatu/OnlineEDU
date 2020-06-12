package com.xatu.onlineedu.entity.vo;

import java.math.BigDecimal;

public class CourseWebVo {

        private String id;
        private String title;
        private BigDecimal price;
        private Integer lessonNum;
        private String cover;
        private Long buyCount;
        private Long viewCount;
        private String description;
        private String teacherId;
        private String teacherName;
        private String intro;
        private String avatar;
        private String subjectLevelOneId;
        private String subjectLevelOne;
        private String subjectLevelTwoId;
        private String subjectLevelTwo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSubjectLevelOneId() {
        return subjectLevelOneId;
    }

    public void setSubjectLevelOneId(String subjectLevelOneId) {
        this.subjectLevelOneId = subjectLevelOneId;
    }

    public String getSubjectLevelOne() {
        return subjectLevelOne;
    }

    public void setSubjectLevelOne(String subjectLevelOne) {
        this.subjectLevelOne = subjectLevelOne;
    }

    public String getSubjectLevelTwoId() {
        return subjectLevelTwoId;
    }

    public void setSubjectLevelTwoId(String subjectLevelTwoId) {
        this.subjectLevelTwoId = subjectLevelTwoId;
    }

    public String getSubjectLevelTwo() {
        return subjectLevelTwo;
    }

    public void setSubjectLevelTwo(String subjectLevelTwo) {
        this.subjectLevelTwo = subjectLevelTwo;
    }
}
