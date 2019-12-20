package com.example.demo3.dao;


import com.example.demo3.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {
  List<Comment> findAll();
  Comment findById(int id);
}
