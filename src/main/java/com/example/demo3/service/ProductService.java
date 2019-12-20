package com.example.demo3.service;

import com.example.demo3.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface ProductService {
    /**
     * 根据id查询
     *
     * @param pid
     * @return
     */
    Product findByPId(int pid);

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * 更新
     *
     * @param product
     * @return
     */
    void update(Product product);

    /**
     * 创建
     *
     * @param product
     * @return
     */
    int create(Product product);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int id);


    /**
     * 查询所有商品类别
     */
    List<String> findAllPclassName(String pid);


}
