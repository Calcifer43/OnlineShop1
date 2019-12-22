package com.example.shop.service;

import com.example.shop.entity.Shop;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ShopService {
    /**
     * 商家增加商铺
     */
    int create(Shop shop);
    /**
     * 根据商家id查找他的所属所有商铺
     * @param sellerId
     * @return
     */
    List<Shop> findBySellerId(int sellerId);

    /**
     * 商家根据自己的id和商铺名字查找商铺（默认除管理员身份都不知道商铺id)
     * @param shopName
     * @return
     */
    Shop findByShopNameAndSellerId(String shopName,int sellerId);

    /**
     * 商家根据自己的id和关键字查询商铺
     * @param keyword
     * @return
     */
    List<Shop> findByShopNameLikeAndSellerId(String keyword,int sellerId);
    /**
     * 删除商铺
     * @param shopId
     * @return
     */
    void delete(int shopId);
    /**
     商家修改商铺
     * @param shop
     * @return
     */
    void update(Shop shop);

    Shop findById(int id);


}
