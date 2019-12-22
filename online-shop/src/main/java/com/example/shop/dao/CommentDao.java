package com.example.shop.dao;


import com.example.shop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {
  List<Comment> findAll();

  /**
   *
   * @param pid
   * @return
   */
  List<Comment> findByPid(int pid);
}
