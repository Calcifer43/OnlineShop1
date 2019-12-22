package com.example.shop.service.impl;


import com.example.shop.dao.ShopDao;
import com.example.shop.entity.Shop;
import com.example.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    /**
     * 添加商铺//可以
     * @param shop
     * @return
     */
    @Override
    public int create(Shop shop) {
        return shopDao.save(shop).getShopId();
    }

    /**
     * 根据商家id查找商铺列表//可以
     * @param sellerId
     * @return
     */
    @Override
    public List<Shop> findBySellerId(int sellerId) {
        return shopDao.findBySellerId(sellerId);
    }

    /**
     * 根据商家id和商铺名字查找商铺//可以
     * @param shopName
     * @param sellerId
     * @return
     */
    @Override
    public Shop findByShopNameAndSellerId(String shopName, int sellerId) {
        return shopDao.findByShopNameAndSellerId(shopName,sellerId);
    }

    /**
     * 根据商家id和模糊的商铺名字关键字查找商铺//可以
     * @param keyword
     * @param sellerId
     * @return
     */
    @Override
    public List<Shop> findByShopNameLikeAndSellerId(String keyword, int sellerId) {
        return shopDao.findByShopNameLikeAndSellerId("%"+keyword+"%",sellerId);
    }

    /**
     * 删除商铺
     * @param shopId
     */
    @Override//可以
    public void delete(int shopId) {
        shopDao.deleteByShopId(shopId);

    }

    /**
     * 更新商铺信息//可以
     * @param shop
     */
    @Override
    public void update(Shop shop) {
        Shop s=shopDao.getOne(shop.getShopId());
        s.setShopName(shop.getShopName());
        s.setShopTel(shop.getShopTel());
        s.setSellerId(shop.getSellerId());
        s.setShopDesc(shop.getShopDesc());
        s.setShopImage(shop.getShopImage());
           shopDao.save(s);

    }

    @Override  //可以
    public Shop findById(int id) {
        return shopDao.getOne(id);
    }


}
