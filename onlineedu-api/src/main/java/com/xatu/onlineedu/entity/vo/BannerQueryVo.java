package com.xatu.onlineedu.entity.vo;

public class BannerQueryVo
{
    private String title;
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    private String end;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
