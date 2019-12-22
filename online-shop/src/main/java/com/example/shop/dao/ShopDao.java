package com.example.shop.dao;

import com.example.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1

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

<<<<<<< HEAD
    void deleteByShopId(int shopId);
=======
    void delete(int shopId);
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1

//    /**
//     * 商家修改商铺
//     * @param shop
//     * @return
//     */
//   void update(Shop shop);

}
