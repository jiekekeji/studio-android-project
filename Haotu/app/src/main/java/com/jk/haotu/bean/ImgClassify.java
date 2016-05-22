package com.jk.haotu.bean;

/**
 * 图分类信息
 * Created by jack on 16/5/22.
 */
public class ImgClassify {

    private String description;
    private String id;
    private String keywords;
    private String name;

    private String seq;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ImgClassify{" +
                "description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", keywords='" + keywords + '\'' +
                ", name='" + name + '\'' +
                ", seq='" + seq + '\'' +
                ", title='" + title + '\'' +
                '}';
    }


}
