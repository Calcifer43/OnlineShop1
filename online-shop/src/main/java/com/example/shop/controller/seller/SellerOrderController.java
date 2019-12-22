package com.example.shop.controller.seller;

import com.example.shop.entity.*;
import com.example.shop.entity.pojo.ResultBean;
import com.example.shop.service.*;
import com.example.shop.service.exception.LoginException;
import com.example.shop.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductServiceImpl productServiceImpl;


    /**
     * 打开订单列表页面
     * @return
     */
    @RequestMapping("/toList.html")
    public String toList() {
        return "seller/order/list";
    }

    /**
     * 获取所有订单的总数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = PageRequest.of(1, 15);
        int total = (int) orderService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    /**
     * 获取所有订单
     * @param pageindex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Order>> listData(int pageindex,
                                            @RequestParam(value = "pageSize", defaultValue = "15") int pageSize, HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("user");
        if(user==null){
            throw new LoginException("请登录");
        }
        int id=user.getId();
        List<Shop> shops=shopService.findBySellerId(id);  //商铺
        List<Product> products1=new ArrayList<>();
        List<Product> products=new ArrayList<>();
        Product product=new Product();
        for(int i=0;i<shops.size();i++){

            products1= productServiceImpl.findAllByShopId(shops.get(i).getShopId(),null).getContent();
            //products1.remo(j);
            products.addAll(products1);
            System.out.println(shops.get(i).getShopId());
        }
        List<OrderItem> orderItems1=new ArrayList<>() ;
        for(int i=0;i<products.size();i++){
            product=products.get(i);
           List<OrderItem> orderItems=orderService.findItemsByPid(product.getPid());
            //orderItems.remove(j);
            orderItems1.addAll(orderItems);
            System.out.println(product.getPid());
        }
        List<Order> orders1=new ArrayList<>();
        for(int i=0;i<orderItems1.size();i++){
            Order order=  orderService.findById(orderItems1.get(i).getOrderId());
            //orders.remove(j);
            orders1.add(order);
            System.out.println(order.getId());
        }
        Pageable pageable = PageRequest.of(pageindex, pageSize);
        List<Order> list = orderService.findAll(pageable).getContent();
        return new ResultBean<>(orders1);
    }

    /**
     * 获取订单项
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDetail.do")
    public ResultBean<List<OrderItem>> getDetail(int orderId) {
        List<OrderItem> list = orderService.findItems(orderId);
        return new ResultBean<>(list);
    }

    /**
     * 发货
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/send.do")
    public ResultBean<Boolean> send(int id) {
        orderService.updateStatus(id,3);
        return new ResultBean<>(true);
    }
}
