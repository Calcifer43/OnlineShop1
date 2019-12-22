package com.example.shop.service;

import com.example.shop.entity.Product;
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
     *
     * @param shopId
     * @param pageable
     * @return
     */
    Page<Product> findAllByShopId(int shopId,Pageable pageable);



    /**
     * 查找热门商品
     * @return
     */
    List<Product> findHotProduct();

    /**
     * 查找最新商品
     * @param pageable
     * @return
     */
    List<Product> findNewProduct(Pageable pageable);

    /**
     * 根据一级分类查找商品
     * @param cid
     * @param pageable
     * @return
     */
    List<Product> findByCid(int cid,Pageable pageable);

    /**
     * 根据二级分类查找商品
     * @param csid
     * @param pageable
     * @return
     */
    List<Product> findByCsid(int csid,Pageable pageable);

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




}
