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
    @Column(name="pid")
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
     * 商品所属类别名
     */
    @Column
    private String pclassName;
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


    private static final long serialVersionUID = 1L;

    public Product() {
    }

    public Product(String pname, Double price, String pclassName, String image, String desc, Integer shopId, Date pdate) {
        this.pname = pname;
        this.price = price;
        this.pclassName = pclassName;
        this.image = image;
        this.desc = desc;
        this.shopId = shopId;
        this.pdate = pdate;
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
    public String getPclassName() {
        return pclassName;
    }

    public void setPclassName(String pclassName) {
        this.pclassName = pclassName;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}