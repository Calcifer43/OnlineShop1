package com.example.shop.dao;

import com.example.shop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {




}
