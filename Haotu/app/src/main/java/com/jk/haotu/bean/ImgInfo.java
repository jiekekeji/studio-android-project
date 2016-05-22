package com.jk.haotu.bean;

import java.util.List;

/**
 * Created by jack on 16/5/21.
 */
public class ImgInfo {

    private String status;
    private String total;
    private List<Tngou> tngous;

    public String getStatus() {
        return status;
    }

    public String getTotal() {
        return total;
    }

    public List<Tngou> getTngous() {
        return tngous;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setTngous(List<Tngou> tngous) {
        this.tngous = tngous;
    }

    @Override
    public String toString() {
        return "ImgInfo{" +
                "status='" + status + '\'' +
                ", total='" + total + '\'' +
                ", tngous=" + tngous +
                '}';
    }

}
