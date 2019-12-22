package com.example.shop.dao;

import com.example.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {


    /**
     * 通过二级分类查找商品列表
     *
     * @param csid
     * @param pageable
     * @return
     */
    List<Product>  findByCsid(int csid, Pageable pageable);

    List<Product> findByCsidIn(List<Integer> csids,Pageable pageable);

    /**
     * 通过标题搜索商品
     *
     * @param keyword
     * @param pageable
     * @return
     */
    List<Product> findByPnameLike(String keyword, Pageable pageable);

    /**
     * 查找某个日期之后上架的商品
     * @param date
     * @param pageable
     * @return
     */
    List<Product> findByPdateAfter(Date date, Pageable pageable);

    /**
     * 查找热门商品
     * @param isHot
     * @param pageable
     * @return
     */
    List<Product> findByIsHot(int isHot,Pageable pageable);

    /**
     * 查询最新商品，最近上新的24个商品
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM (SELECT  * FROM product ORDER BY pdate DESC limit 0,24) a /*#pageable*/",nativeQuery = true)
    List<Product> findNew(Pageable pageable);

    /**
     * 根据商铺号进行商品的查询
     * @param shopId
     * @param pageable
     * @return
     */
     Page<Product> findAllByShopId(int shopId, Pageable pageable);



}
