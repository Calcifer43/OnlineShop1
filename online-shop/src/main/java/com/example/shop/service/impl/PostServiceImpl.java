package com.example.shop.service.impl;

import com.example.shop.dao.PostDao;
import com.example.shop.entity.Post;
import com.example.shop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;


    public int create(Post post) {
        return postDao.save(post).getPostId();
    }


    public void save(Post post) {
        postDao.save(post);
    }


    public List<Post> findAllPost(){
        return postDao.findAll();
    }
    public List<Post> findAllPostByUserId(int id){
        return postDao.findAllByUserId(id);
    }
    public Post findPostByPostId(int id){
        return postDao.findPostByPostId(id);
    }

}
