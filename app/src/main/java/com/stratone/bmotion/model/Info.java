package com.stratone.bmotion.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("NO")
    @Expose
    private Integer nO;
    @SerializedName("NotificationId")
    @Expose
    private String notificationId;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Title")
    @Expose
    private String title;

    public Integer getNO() {
        return nO;
    }

    public void setNO(Integer nO) {
        this.nO = nO;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
