package com.xatu.onlineedu.entity.vo;

import java.io.Serializable;

public class VideoVo implements Serializable {
    private String id;
    private String title;
    private Boolean isFree;

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

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
