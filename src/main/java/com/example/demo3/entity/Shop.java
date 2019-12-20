package com.example.demo3.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="shop")
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shopId")
    private Integer shopId;
    /**
     *店铺名
     */
    @Column(name="shopName")
    private String shopName;
    /**
     * 店家Id
     */
    @Column(name="sellerId")
    private Integer sellerId;

    private static final long serialVersionUID = 1L;


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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
