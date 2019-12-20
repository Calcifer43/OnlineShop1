package com.example.demo3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer postId;  //帖子Id
    /**
     * 帖子标题
     */
    @Column
    private String postTitle;
    /**
     * 帖子信息
     */
    @Column
    private String postInfo;
    /**
     * 发帖人Id
     */
    @Column
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Post(String postTitle, String postInfo, Integer userId) {
        this.postTitle = postTitle;
        this.postInfo = postInfo;
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
