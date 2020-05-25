package com.xatu.onlineedu.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChapterVo implements Serializable {
    private String id;
    private String title;
    private List<VideoVo> videoVoList = new ArrayList<VideoVo>();

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

    public List<VideoVo> getVideoVoList() {
        return videoVoList;
    }

    public void setVideoVoList(List<VideoVo> videoVoList) {
        this.videoVoList = videoVoList;
    }
}
