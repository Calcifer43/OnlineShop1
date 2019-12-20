package com.example.demo3.dao;

import com.example.demo3.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsernameAndPassword(String username, String pwd);
}
