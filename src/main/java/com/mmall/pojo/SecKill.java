package com.mmall.pojo;

import java.util.Date;

public class SecKill {

    private String title;
    private Date startTime;
    private Date endTime;
    private String status;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SecKill{" +
                "title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
