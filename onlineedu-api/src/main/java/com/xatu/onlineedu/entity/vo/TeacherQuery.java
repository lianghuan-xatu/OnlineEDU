package com.xatu.onlineedu.entity.vo;

public class TeacherQuery {
    private String name;
    private Integer level;
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    private String end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
