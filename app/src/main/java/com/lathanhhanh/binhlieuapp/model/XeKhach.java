package com.lathanhhanh.binhlieuapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XeKhach {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tuyen")
    @Expose
    private String tuyen;
    @SerializedName("bienso")
    @Expose
    private String bienso;
    @SerializedName("giave")
    @Expose
    private String giave;
    @SerializedName("soghe")
    @Expose
    private String soghe;
    @SerializedName("thoigian1")
    @Expose
    private String thoigian1;
    @SerializedName("thoigian2")
    @Expose
    private String thoigian2;
    @SerializedName("sodienthoai")
    @Expose
    private String sodienthoai;
    @SerializedName("ghichu")
    @Expose
    private String ghichu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTuyen() {
        return tuyen;
    }

    public void setTuyen(String tuyen) {
        this.tuyen = tuyen;
    }

    public String getBienso() {
        return bienso;
    }

    public void setBienso(String bienso) {
        this.bienso = bienso;
    }

    public String getGiave() {
        return giave;
    }

    public void setGiave(String giave) {
        this.giave = giave;
    }

    public String getSoghe() {
        return soghe;
    }

    public void setSoghe(String soghe) {
        this.soghe = soghe;
    }

    public String getThoigian1() {
        return thoigian1;
    }

    public void setThoigian1(String thoigian1) {
        this.thoigian1 = thoigian1;
    }

    public String getThoigian2() {
        return thoigian2;
    }

    public void setThoigian2(String thoigian2) {
        this.thoigian2 = thoigian2;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
