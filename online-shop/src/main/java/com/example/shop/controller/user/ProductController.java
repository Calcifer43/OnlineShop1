package com.example.shop.controller.user;

import com.example.shop.entity.*;
import com.example.shop.entity.pojo.ResultBean;
import com.example.shop.service.*;
import com.example.shop.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mall/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("/comment.do")
    public void  comment(
            int pid,
            String name,
            int likes,
            String content,
            int commentnum,
            HttpServletResponse response,HttpServletRequest request) throws IOException {
      SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String datetime = tempDate.format(new Date());
       User user= (User) request.getSession().getAttribute("user");
        //int n=(int)(Math.random()*100)+1;
        //Integer A=Integer.valueOf(n);
        Comment comment = new Comment();
        comment.setPid(pid);
        comment.setName(name);
        comment.setTime(datetime);
        comment.setLikes(likes);
        comment.setContent(content);
        //comment.setImage("/mall/admin/product/img/10.jpg");
        comment.setCommentnum(commentnum);
        commentService.create(comment);
        commentService.save(comment);
        // 重定向刷新
        response.sendRedirect("/mall/product/get.html?id="+pid);
        //response.sendRedirect("toList.html");
    }



    /**
     * 查找热门商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/hot.do")
    public ResultBean<List<Product>> getHotProduct() {
        List<Product> products = productServiceImpl.findHotProduct();
        return new ResultBean<>(products);
    }


    @ResponseBody
    @RequestMapping("/checkComment.do")
    public boolean checkComment(int pid,HttpServletRequest request) throws Exception {
        Product product = productServiceImpl.findByPId(pid);
        List<OrderItem> orderItems=orderService.findItemsByPid(pid);
        User user= (User) request.getSession().getAttribute("user");
        if(user==null){
             throw new Exception("未登录！请重新登录");
        }
        int userId=user.getId();
        for(int i=0;i<orderItems.size();i++){
            int orderId=orderItems.get(i).getOrderId();
            Order order=orderService.findById(orderId);
            if(order.getUserId()==userId){
                if(order.getState()==4)
                   return true;
            }
        }
        return false;
    }



    /**
     * 查找最新商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/new.do")
    public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Product> products = productServiceImpl.findNewProduct(pageable);
        return new ResultBean<>(products);
    }

    /**
     * 打开分类查看商品页面
     *
     * @return
     */
    @RequestMapping("/category.html")
    public String toCatePage(int cid, Map<String, Object> map) {
        Classification classification = classificationService.findById(cid);
        map.put("category", classification);
        return "mall/product/category";
    }

    /**
     * 按一级分类查找商品
     *
     * @param cid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/category.do")
    public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Product> products = productServiceImpl.findByCid(cid, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 按二级分类查找商品
     *
     * @param csId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/categorySec.do")
    public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Product> products = productServiceImpl.findByCsid(csId, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 根据一级分类查询它所有的二级分类
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCategorySec.do")
    public ResultBean<List<Classification>> getCategorySec(int cid){
        List<Classification> list = classificationService.findByParentId(cid);
        return new ResultBean<>(list);
    }




    /**
     * 通过商品pid获取该商品的信息，返回数据以json形式返回，读取该数据以老师的实验图书商城一样
     *测试路径可以为：   http://localhost:8080/searchProduct?pid=11
     * @param pid
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/searchProduct")
    @ResponseBody
    public void searchProduct(Integer pid,HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        Product product=productServiceImpl.findByPId(pid);
        ObjectMapper mapper=new ObjectMapper();
        String jsonstr=mapper.writeValueAsString(product);
        PrintWriter out=response.getWriter();
        out.write(jsonstr);
    }

    /**
     * 可以通过路径测试：http://localhost:8080/searchProductByKeyWord?keyword=联想
     * @param keyword
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/searchProductByKeyWord")
    @ResponseBody
    public void searchProductByKeyWord(String keyword, Pageable pageable, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        String keywordLike="%"+keyword+"%";
        List<Product> products=productServiceImpl.findByPnameLike(keywordLike,pageable);
        ObjectMapper mapper=new ObjectMapper();
        String jsonstr=mapper.writeValueAsString(products);
        PrintWriter out=response.getWriter();
        out.write(jsonstr);
    }


    @RequestMapping("/findAllProduct")
    @ResponseBody
    public void search(String keyword,HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        List<Product> products=productServiceImpl.findAllProduct();
        ObjectMapper mapper=new ObjectMapper();
        String jsonstr=mapper.writeValueAsString(products);
        PrintWriter out=response.getWriter();
        out.write(jsonstr);
    }

    /**
     * 打开商品详情页面
     *
     * @param id
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/get.html")
    public String toProductPage(int id,HttpServletRequest request, Map<String, Object> map) {
        Product product = productServiceImpl.findByPId(id);
        map.put("product", product);
        User user= (User) request.getSession().getAttribute("user");
        List<Comment> comments = commentService.findCommentByPid(id);
        map.put("comments", comments);
        map.put("user", user);
        return "mall/product/info";
    }



    @RequestMapping("/toCart.html")
    public String toCart(){
        return "mall/product/cart";
    }



    /**
     * 加购物车
     *
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCart.do")
    public ResultBean<Boolean> addToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.addCart(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * 移除购物车
     *
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCart.do")
    public ResultBean<Boolean> delToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.remove(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * 查看购物车商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listCart.do")
    public ResultBean<List<OrderItem>> listCart(HttpServletRequest request) throws Exception {
        List<OrderItem> orderItems = shopCartService.listCart(request);
        return new ResultBean<>(orderItems);
    }



}
