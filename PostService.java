package com.example.shop.service;

import com.example.shop.entity.Post;

import java.util.List;

public interface PostService {
    /**
     *
     * @param post
     * @return
     */
    int create(Post post);


    /**
     *
     * @param post
     */
    void save(Post post);

}
