package com.example.shop.dao;

import com.example.shop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     * 通过关键字搜索商品
     *
     * @param keyword
     * @return
     */
    List<Product> findByPnameLike(String keyword);

    /**
     * 查询所有商品类别
     * @param pid
     */
    @Query(value="select distinct pclass_name from product",nativeQuery = true)
    List<String> findAllPclassName(String pid);

    /**
     * 根据商品类别查找该种商品
     * @param pclassName
     * @return
     */
    List<Product> findAllByPclassName(String pclassName);



}
