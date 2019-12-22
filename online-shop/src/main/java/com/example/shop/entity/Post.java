package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
    private String postImage;

    private static final long serialVersionUID = 1L;

    public Post(String postTitle, String postInfo, Integer userId) {
        this.postTitle = postTitle;
        this.postInfo = postInfo;
        this.userId = userId;
    }

    public Post() {
        super();
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
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
