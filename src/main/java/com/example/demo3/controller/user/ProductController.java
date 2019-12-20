package com.example.demo3.controller.user;

import com.example.demo3.entity.Comment;
import com.example.demo3.entity.OrderItem;
import com.example.demo3.entity.Product;
import com.example.demo3.entity.User;
import com.example.demo3.entity.pojo.ResultBean;
import com.example.demo3.service.CommentService;
import com.example.demo3.service.ShopCartService;
import com.example.demo3.service.impl.ProductServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private CommentService commentService;


    @RequestMapping("/comment.do")
    public String  comment(
            int id,
            String name,
            String time,
            int likes,
            String content,
            String image,
            int commentnum,
            HttpServletResponse response,HttpServletRequest request) throws IOException {
      SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String datetime = tempDate.format(new Date());
       User user= (User) request.getSession().getAttribute("user");
        int n=(int)(Math.random()*100)+1;
        Integer A=Integer.valueOf(n);
        Comment comment = new Comment();
        comment.setId(id);
        comment.setName(user.getUsername());
        comment.setTime(datetime);
        comment.setLikes(likes);
        comment.setContent(content);
        comment.setImage("/mall/admin/product/img/10.jpg");
        comment.setCommentnum(commentnum);
        commentService.create(comment);
        commentService.save(comment);
        // 重定向刷新
        return "mall/product/info";
    }

    /**
     * 获取商品分类信息
     * 测试路径可以为：http://localhost:8080/getProductClass
     *@param pid
     */
    @RequestMapping("/getProductClass")
    @ResponseBody
    public void  getProductClass(String pid, HttpServletRequest request , HttpServletResponse response) throws IOException {
        List<String> PClassNames=productServiceImpl.findAllPclassName(pid);
        for(int i=0;i<PClassNames.size();i++){
            System.out.println(PClassNames.get(i));
        }
        //暂时没有使用过滤器，各位可以实现它
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        //将数据存储到json中，这个pid参数是没有用的，因为我们要查询的是全部种类，下面的一样
        ObjectMapper mapper=new ObjectMapper();
        String jsonstr=mapper.writeValueAsString(PClassNames);
        PrintWriter out=response.getWriter();
        out.write(jsonstr);
        //return  PClassNames;
    }


    /**
     * 获取某一类商品的所有商品信息
     * 测试路径可以使用   http://localhost:8080/displayProduct?pclassName=图书
     * @param pclassName
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/displayProduct")
    @ResponseBody
    public void displayProduct(String pclassName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        System.out.println(pclassName);
        List<Product> products=productServiceImpl.findAllByPclassName(pclassName);
        ObjectMapper mapper=new ObjectMapper();
        String jsonstr=mapper.writeValueAsString(products);
        PrintWriter out=response.getWriter();
        out.write(jsonstr);
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
    public void searchProductByKeyWord(String keyword,HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        String keywordLike="%"+keyword+"%";
        List<Product> products=productServiceImpl.findByPnameLike(keywordLike);
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
        List<Comment> comment = commentService.getCommentList();
        map.put("comment", comment);
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
