package com.example.shop.dao;

import com.example.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     * @return
     */

   // User findByUsernameAndPassword(String username, String password);
    User findByUsernameAndPassword(String username,String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     *
     */
    List<User> findByUsername(String username);


}
