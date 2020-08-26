package com.stratone.bmotion.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.bmotion.model.Fuel;
import com.stratone.bmotion.model.Info;

import java.util.List;

public class ResponseInfo {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<Info> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Info> getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }
}
