package com.example.shop.service.impl;

import com.example.shop.dao.CommentDao;
import com.example.shop.entity.Comment;
import com.example.shop.service.CommentService;
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
    public List<Comment> findCommentByPid(int id) {
        return commentDao.findByPid(id);
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
