package com.example.shop.dao;

import com.example.shop.entity.Comment;
import com.example.shop.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post,Integer> {

    /**
     *
     */
    List<Post> findAll();

    /**
     *
     * @param id
     * @return
     */
    List<Post> findAllByPostId(int id);

    /**
     *
     * @param id
     * @return
     */
    List<Post> findAllByUserId(int id);

    /**
     *
     * @param id
     * @return
     */
    Post findPostByPostId(int id);

}
