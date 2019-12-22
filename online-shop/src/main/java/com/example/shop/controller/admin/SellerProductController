package com.example.shop.controller.seller;

import com.example.shop.entity.Classification;
import com.example.shop.entity.Product;
import com.example.shop.entity.Shop;
import com.example.shop.entity.pojo.ResultBean;
import com.example.shop.service.ClassificationService;
import com.example.shop.service.ProductService;
import com.example.shop.service.ShopService;
import com.example.shop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ShopService shopService;

    @RequestMapping("/toList.html")
    public String toList() {
        return "seller/product/list";
    }

    @RequestMapping("/toAdd.html")
    public String toAdd() {
        return "seller/product/add";
    }

    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
        Product product = productService.findByPId(id);
        Classification classification = classificationService.findById(product.getCsid());
        product.setCategorySec(classification);
        map.put("product", product);
        return "seller/product/edit";
    }

    @RequestMapping("/createProduct.html")
    public String toCreateProduct(int id,Map<String,Object> map) {
        Shop shop=shopService.findById(id);
        map.put("shop",shop);
        return "seller/product/add";
    }

    @RequestMapping("/ShopProduct.html")
    public String shopProduct(int id,Map<String,Object> map) {
        Shop shop=shopService.findById(id);
        map.put("shop",shop);
        return "seller/product/list";
    }


    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Product>> listProduct(int shopId,int pageindex,
                                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = PageRequest.of(pageindex, pageSize);
        List<Product> list = productService.findAllByShopId(shopId,pageable).getContent();
        return new ResultBean<>(list);
    }

    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal(int shopId) {
        Pageable pageable = PageRequest.of(1, 15);
        int total = (int) productService.findAllByShopId(shopId,pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public ResultBean<Boolean> del(int id) {
        productService.delById(id);
        return new ResultBean<>(true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    public void add(MultipartFile image,
                    String title,
                    Double marketPrice,
                    int isHot,
                    int shopId,
                    String desc,
                    int csid,
                    HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        Product product = new Product();
        product.setPname(title);
        product.setPrice(marketPrice);
        product.setDesc(desc);
        product.setIsHot(isHot);
        product.setCsid(csid);
        product.setShopId(shopId);
        product.setPdate(new Date());
        String imgUrl = FileUtil.saveFile(image);
        product.setImage(imgUrl);
        int id = productService.create(product);
        if (id <= 0) {
            request.setAttribute("message", "添加失败！");
            request.getRequestDispatcher("toAdd.html").forward(request, response);
            response.sendRedirect("toAdd.html");
            //return "/seller/product/toAdd.html";
            //return "/seller/product/list";
        } else {
            response.sendRedirect("toEdit.html?id="+id);
            //return "/seller/product/list";
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public String update(int id,
                       String title,
                       Double marketPrice,
                       String desc,
                       int csid,
                       int isHot,
                       MultipartFile image,Map<String,Object> map,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        Product product = productService.findByPId(id);
        int shopId=product.getShopId();
        Shop shop=shopService.findById(shopId);
        map.put("shop",shop);
        product.setPname(title);
        product.setPrice(marketPrice);
        product.setDesc(desc);
        product.setIsHot(isHot);
        product.setCsid(csid);
        product.setPdate(new Date());
        String imgUrl = FileUtil.saveFile(image);
        if (isNotBlank(imgUrl)) {
            product.setImage(imgUrl);
        }
        boolean flag = false;
        try {
            productService.update(product);
            flag = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (!flag) {
            request.setAttribute("message", "更新失败！");
        }
        return "/seller/product/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/img/{filename:.+}")
    public void getImage(@PathVariable(name = "filename", required = true) String filename,
                         HttpServletResponse res) throws IOException {
        File file = new File("file/" + filename);
        if (file != null && file.exists()) {
            res.setHeader("content-type", "application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            res.setContentLengthLong(file.length());
            Files.copy(Paths.get(file.toURI()), res.getOutputStream());
        }
    }


}
