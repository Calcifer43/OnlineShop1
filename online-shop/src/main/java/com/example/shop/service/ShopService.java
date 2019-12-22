package com.example.shop.service;

<<<<<<< HEAD


import com.example.shop.entity.Shop;

=======
import com.example.shop.entity.Shop;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1
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
<<<<<<< HEAD
    Shop findByShopNameAndSellerId(String shopName, int sellerId);
=======
    Shop findByShopNameAndSellerId(String shopName,int sellerId);
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1

    /**
     * 商家根据自己的id和关键字查询商铺
     * @param keyword
     * @return
     */
<<<<<<< HEAD
    List<Shop> findByShopNameLikeAndSellerId(String keyword, int sellerId);
=======
    List<Shop> findByShopNameLikeAndSellerId(String keyword,int sellerId);
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1
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
