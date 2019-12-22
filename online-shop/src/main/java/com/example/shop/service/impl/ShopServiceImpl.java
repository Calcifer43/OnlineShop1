package com.example.shop.service.impl;

<<<<<<< HEAD

=======
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1
import com.example.shop.dao.ShopDao;
import com.example.shop.entity.Shop;
import com.example.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

=======
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1
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
<<<<<<< HEAD
        shopDao.deleteByShopId(shopId);
=======
        shopDao.delete(shopId);
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1

    }

    /**
     * 更新商铺信息//可以
     * @param shop
     */
    @Override
    public void update(Shop shop) {
<<<<<<< HEAD
        Shop s=shopDao.getOne(shop.getShopId());
=======
        Shop s=shopDao.findOne(shop.getShopId());
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1
        s.setShopName(shop.getShopName());
        s.setShopTel(shop.getShopTel());
        s.setSellerId(shop.getSellerId());
        s.setShopDesc(shop.getShopDesc());
        s.setShopImage(shop.getShopImage());
           shopDao.save(s);

    }

    @Override  //可以
    public Shop findById(int id) {
<<<<<<< HEAD
        return shopDao.getOne(id);
=======
        return shopDao.findOne(id);
>>>>>>> 1cb429456fccdb2f42c77c9ab7f1e1a4fbe40af1
    }


}
