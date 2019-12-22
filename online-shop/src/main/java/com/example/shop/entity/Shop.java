package com.example.shop.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="shop")
public class Shop implements Serializable {
    /**
     * 商铺id
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shop_id")
    private Integer shopId;
    /**
     *店铺名
     */
    @Column(name="shop_name")
    private String shopName;
    /**
     * 店家Id
     */
    @Column(name="seller_id")
    private Integer sellerId;
    /**
     * 商店的描述
     */
    @Column(name="shop_desc",columnDefinition = "text")
   private  String shopDesc;
    /**
     * 商店的图片
     */
    @Column(name="shop_image")
    private  String shopImage;
    /**
     * 商店的电话
     */
    @Column(name="shop_tel")
    private String shopTel;

    private static final long serialVersionUID = 1L;

    public Shop() {
        this.shopName = shopName;
        this.sellerId = sellerId;
        this.shopDesc = shopDesc;
        this.shopImage = shopImage;
        this.shopTel = shopTel;
    }

    public Shop(String shopName, Integer sellerId) {
        this.shopName = shopName;
        this.sellerId = sellerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(shopId, shop.shopId) &&
                Objects.equals(shopName, shop.shopName) &&
                Objects.equals(sellerId, shop.sellerId) &&
                Objects.equals(shopDesc, shop.shopDesc) &&
                Objects.equals(shopImage, shop.shopImage) &&
                Objects.equals(shopTel, shop.shopTel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, shopName, sellerId, shopDesc, shopImage, shopTel);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", sellerId=" + sellerId +
                ", shopDesc='" + shopDesc + '\'' +
                ", shopImage='" + shopImage + '\'' +
                ", shopTel='" + shopTel + '\'' +
                '}';
    }
}
