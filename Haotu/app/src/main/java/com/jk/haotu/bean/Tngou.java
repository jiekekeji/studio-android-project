package com.jk.haotu.bean;

/**
 * Created by jack on 16/5/21.
 */
public class Tngou {

    private String count;
    private String fcount;
    private String galleryclass;
    private String id;

    private String img;
    private String rcount;
    private String size;
    private String time;
    private String title;

    public String getCount() {
        return count;
    }

    public String getFcount() {
        return fcount;
    }

    public String getGalleryclass() {
        return galleryclass;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getRcount() {
        return rcount;
    }

    public String getSize() {
        return size;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setFcount(String fcount) {
        this.fcount = fcount;
    }

    public void setGalleryclass(String galleryclass) {
        this.galleryclass = galleryclass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRcount(String rcount) {
        this.rcount = rcount;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tngou{" +
                "count='" + count + '\'' +
                ", fcount='" + fcount + '\'' +
                ", galleryclass='" + galleryclass + '\'' +
                ", id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", rcount='" + rcount + '\'' +
                ", size='" + size + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                '}';
    }


}
