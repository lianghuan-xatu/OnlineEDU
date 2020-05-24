package com.xatu.onlineedu.entity.subject;

import java.util.ArrayList;
import java.util.List;

public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> twoSubjectList = new ArrayList<TwoSubject>();

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

    public List<TwoSubject> getTwoSubjectList() {
        return twoSubjectList;
    }

    public void setTwoSubjectList(List<TwoSubject> twoSubjectList) {
        this.twoSubjectList = twoSubjectList;
    }
}
