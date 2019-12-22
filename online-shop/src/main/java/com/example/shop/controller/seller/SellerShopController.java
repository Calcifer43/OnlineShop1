package com.example.shop.controller.seller;

import com.example.shop.entity.Shop;
import com.example.shop.service.ShopService;
import com.example.shop.utils.FileUtil;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Controller
@RequestMapping("/seller/shop")
public class SellerShopController {
       @Autowired
    private ShopService shopService;

    /**
     * 增加商铺页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "seller/shop/add";
    }

    /**
     * 查询商铺并修改商铺添加商平的列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model) {

        return "seller/shop/list";
    }

    @RequestMapping("/createProduct")
    public String createProduct(Model model) {

        return "seller/shop/addProduct";
    }

    /**
     * 删除商铺的页面
     * @param model
     * @return
     */
    @RequestMapping("/toDel")
    public String toDel(int id, Model model) {
        List<Shop> shopList=shopService.findBySellerId(id);
        model.addAttribute("shops", shopList);
        return "seller/shop/del";
    }

    /**
     * 修改和增加商品的页面
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(int id, Model model) {
        Shop shop=shopService.findById(id);
        model.addAttribute("shop", shop);
        return "seller/shop/edit";
    }

    /**
     * 表单返回数据，添加商铺
     * @param image
     * @param desc
     * @param shoptel
     * @param sellerid
     * @param shopname
     * @param request
     * @param response
     * @throws Exception
     */
   @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    public void add(Model model,
                    MultipartFile image,
                    String desc,
                    String shoptel,
                    String sellerid,
                    String shopname,
                    HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
      response.setCharacterEncoding("UTF-8");
     response.setContentType("text/html;charset=UTF-8");
        Shop shop=new Shop();
        shop.setShopDesc(desc);
        shop.setShopTel(shoptel);
        shop.setShopName(shopname);
      String imgUrl = FileUtil.saveFile(image);
       shop.setShopImage(imgUrl);
        shop.setSellerId(Integer.parseInt(sellerid));
        int id = shopService.create(shop);
     String s="12";
        if (id <= 0) {

       s = "添加失败！";


        } else {
      s="添加成功！";


        }
       System.out.println(s);
      response.getWriter().print(s);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public void update(int id,
                    MultipartFile image,
                       String desc,
                       String shoptel,
                       String shopname,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
       Shop shop= shopService.findById(id);
        shop.setShopDesc(desc);
        shop.setShopTel(shoptel);
        shop.setShopName(shopname);
//        shop.setSellerId(sellerid);
        String imgUrl = FileUtil.saveFile(image);
        if (isNotBlank(imgUrl)) {
            shop.setShopImage(imgUrl);
        }
        boolean flag = false;
        try {
            shopService.update(shop);
            flag = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (!flag) {
            request.setAttribute("message", "更新失败！");
        }
        response.sendRedirect("toList");
    }


    /**
     * 删除店铺按钮删除
     * @param id
     * @return
     */
    @RequestMapping("/del.do")
    public String delete(long id) {
           int iid=(int)id;
               shopService.delete(iid);
       return "redirect:/seller/shop/toDel";
    }

    @ResponseBody
    @RequestMapping("/list.do")
    public void listShop(int id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        List<Shop> shops=shopService.findBySellerId(id);
        JSONArray jsonArray=new JSONArray();
        jsonArray.addAll(shops);
        System.out.println(jsonArray.toString());
        response.getWriter().print(jsonArray.toString());
    }

    @RequestMapping("/shop.do")
    @ResponseBody
    public void findShop(String name, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        List<Shop> shops=shopService.findByShopNameLikeAndSellerId(name,1);
        JSONArray jsonArray=new JSONArray();
        jsonArray.addAll(shops);
        System.out.println(jsonArray.toString());
        response.getWriter().print(jsonArray.toString());
    }


}
