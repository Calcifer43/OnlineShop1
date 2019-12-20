package com.example.demo3.service.impl;

import com.example.demo3.dao.CommentDao;
import com.example.demo3.entity.Comment;
import com.example.demo3.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    @RequestMapping("/cache_3")
    @CacheEvict(value = "commemt")
    public List<Comment> getCommentList() {
        return commentDao.findAll();
    }

    @Override
    public Comment findById(int id) {
        return commentDao.getOne(id);
    }

    @Override
    public Comment findCommentById(int id) {
        return commentDao.findById(id);
    }

    @Override
    public void save(Comment comment) {
          commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public int create(Comment comment) {
        int n=(int)(Math.random()*100)+1;
        Integer A=Integer.valueOf(n);
        return commentDao.save(comment).getId()*A;
    }
}
