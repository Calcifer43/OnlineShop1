package com.example.shop.service.impl;

import com.example.shop.dao.ProductDao;
import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override //通过产品Id查找该产品的信息
    public Product findByPId(int pid) {
        return productDao.getOne(pid);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public int create(Product product) {
       return productDao.save(product).getPid();
    }

    @Override
    public void delById(int pid) {
        productDao.deleteById(pid);
    }


    //查找商品的所有分类
    @Override
    public List<String> findAllPclassName(String pid) {
        return productDao.findAllPclassName(pid);
    };

    //根据商品类别(中文)查找出该类别的商品
    public List<Product>  findAllByPclassName(String pclassName){
           return productDao.findAllByPclassName(pclassName);
    };
    //根据关键词查询商品
    public  List<Product> findByPnameLike(String keyword){
        return productDao.findByPnameLike(keyword);
    };

    //查询所有商品
    public  List<Product> findAllProduct(){
        return productDao.findAll();
    };
}
