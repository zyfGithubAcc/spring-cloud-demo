package com.fufu.entity;

import java.util.Date;

public class BlogVisitor {
    private int id ;
    private String visitorName ;
    private String visitorGender;
    private String visitorReason;
    private Date visitTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorGender() {
        return visitorGender;
    }

    public void setVisitorGender(String visitorGender) {
        this.visitorGender = visitorGender;
    }

    public String getVisitorReason() {
        return visitorReason;
    }

    public void setVisitorReason(String visitorReason) {
        this.visitorReason = visitorReason;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}