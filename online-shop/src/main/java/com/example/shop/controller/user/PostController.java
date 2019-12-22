package com.example.shop.controller.user;


import com.example.shop.entity.Order;
import com.example.shop.entity.Post;
import com.example.shop.entity.User;
import com.example.shop.entity.pojo.ResultBean;
import com.example.shop.service.impl.PostServiceImpl;
import com.example.shop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mall/post")
public class PostController {

    @RequestMapping("/toList.html")
    public String toPost(){
        return "mall/post/list";
    }

    @RequestMapping("/toAdd.html")
    public String toAddPost(){
        return "mall/post/add";
    }

    @RequestMapping("/toPostConcret.html")
    public String toPostConcret(){
        return "mall/post/postConcret";
    }


    @Autowired
    private PostServiceImpl postServiceImpl;

    //获得所有的帖子
    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<Post>> getListPost(HttpServletRequest request) {
        List<Post>  posts=postServiceImpl.findAllPost();
        return  new ResultBean<>(posts);
    }



    /**
     *通过用户Id查找其发的帖子
     *
     * @return
     */
    @RequestMapping("/queryMyPost.do")
    @ResponseBody
    public ResultBean<List<Post>> getPostByUserId(HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("user");
        int id=user.getId();
        List<Post>  posts=postServiceImpl.findAllPostByUserId(id);
        return  new ResultBean<>(posts);
    }


  //保存新创建的帖子
  @RequestMapping(value = "/post.do",method = RequestMethod.POST )
  @ResponseBody
  public void  getPostByUserId(String postTitle, MultipartFile postImage, String postInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Post post=new Post();
        User user= (User) request.getSession().getAttribute("user");
        post.setPostTitle(postTitle);
        post.setUserId(user.getId());
        post.setPostInfo(postInfo);
        String imgUrl = FileUtil.saveFile1(postImage);
        post.setPostImage(imgUrl);
        int id=postServiceImpl.create(post);
        int userid=user.getId();
        postServiceImpl.save(post);
        System.out.println(post.getPostId());
       if (id <= 0) {
          request.setAttribute("message", "添加失败！");
          request.getRequestDispatcher("/mall/post/toAdd.html").forward(request, response);
      } else {
          request.getRequestDispatcher("/mall/post/toList.html").forward(request, response);
      }

  }
    //查看该帖子详情

    /**
     *
     * @param postid
     * @param map
     * @return
     */
    @RequestMapping("/getPostDetail.do")
    public String getPostDetail(int postid,  Map<String, Object> map) {
        Post post=postServiceImpl.findPostByPostId(postid);
        map.put("post",post);
        return  "mall/post/postConcret";
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
