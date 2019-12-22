package com.example.shop.dao;

import com.example.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopDao extends JpaRepository<Shop, Integer> {
    //以下都是以商家的身份对shop的操作，管理员可以进行补充

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
   Shop findByShopNameAndSellerId(String shopName, int sellerId);

     /**
     * 商家根据自己的id和关键字查询商铺
     * @param keyword
     * @return
     */

     List<Shop> findByShopNameLikeAndSellerId(String keyword, int sellerId);
    /**
     * 删除商铺
     * @param shopId
     * @return
     */

    void deleteByShopId(int shopId);

//    /**
//     * 商家修改商铺
//     * @param shop
//     * @return
//     */
//   void update(Shop shop);

}
