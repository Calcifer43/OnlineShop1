package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer pid;
    /**
     * 商品名
     */
    @Column(name="pname")
    private String pname;
    /**
     * 商品价格
     */
    @Column
    private Double price;

    /**
     * 二级分类Id
     */
    @Column
    private int csid;

    /**
     * 是否热门商品
     */
    @Column
    private Integer isHot;

    /**
     * 主图
     */
    @Column
    private String image;
    /**
     * 描述
     */
    @Column(name = "`desc`", columnDefinition = "text")
    private String desc;
    /**
     * 商品所属店铺Id
     */
    @Column
    private Integer shopId;
    /**
     * 商品创建日期
     */
    @Column
    private Date pdate;


    @Transient
    private Classification categorySec;



    private static final long serialVersionUID = 1L;

    public Product() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Classification getCategorySec() {
        return categorySec;
    }

    public void setCategorySec(Classification categorySec) {
        this.categorySec = categorySec;
    }
}