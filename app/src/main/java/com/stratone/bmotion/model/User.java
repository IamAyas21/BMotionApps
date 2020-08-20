package com.stratone.bmotion.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("NIP")
    @Expose
    private String nIP;
    @SerializedName("RoleId")
    @Expose
    private Integer roleId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Profession")
    @Expose
    private String profession;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Phone")
    @Expose
    private String phone;

    @SerializedName("KTP")
    @Expose
    private String ktp;
    @SerializedName("ExpDate")
    @Expose
    private String expdate;

    @SerializedName("Quota")
    @Expose
    private String quota;
    @SerializedName("PurchaseBBM")
    @Expose
    private String purchaseBBM;

    @SerializedName("Verification")
    @Expose
    private String verfication;

    public String getNIP() {
        return nIP;
    }

    public void setNIP(String nIP) {
        this.nIP = nIP;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getKTP() {
        return ktp;
    }

    public void setKTP(String ktp) {
        this.ktp = ktp;
    }
    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }
    public String getPurchaseBBM() {
        return purchaseBBM;
    }

    public void setPurchaseBBM(String purchaseBBM) {
        this.purchaseBBM = purchaseBBM;
    }

    public String getVerification() {
        return verfication;
    }

    public void setVerification(String verfication) {
        this.verfication = verfication;
    }
}
