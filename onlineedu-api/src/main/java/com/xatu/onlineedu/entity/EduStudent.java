package com.xatu.onlineedu.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 学生
 * </p>
 *
 * @author LiangHuan
 * @since 2020-06-04
 */
@TableName("edu_student")
public class EduStudent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 章节ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 语文成绩
     */
    private BigDecimal chScore;

    /**
     * 数学成绩
     */
    private BigDecimal mathScore;

    /**
     * 英语成绩
     */
    private BigDecimal engScore;

    /**
     * Java成绩
     */
    private BigDecimal javaScore;

    /**
     * 班级
     */
    private String className;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getChScore() {
        return chScore;
    }

    public void setChScore(BigDecimal chScore) {
        this.chScore = chScore;
    }

    public BigDecimal getMathScore() {
        return mathScore;
    }

    public void setMathScore(BigDecimal mathScore) {
        this.mathScore = mathScore;
    }

    public BigDecimal getEngScore() {
        return engScore;
    }

    public void setEngScore(BigDecimal engScore) {
        this.engScore = engScore;
    }

    public BigDecimal getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(BigDecimal javaScore) {
        this.javaScore = javaScore;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "EduStudent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", chScore=" + chScore +
                ", mathScore=" + mathScore +
                ", engScore=" + engScore +
                ", javaScore=" + javaScore +
                ", className='" + className + '\'' +
                ", sort=" + sort +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
