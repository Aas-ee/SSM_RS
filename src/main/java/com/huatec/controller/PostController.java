package com.huatec.controller;

import com.huatec.domain.Admin;
import com.huatec.domain.Post;
import com.huatec.service.PostService;
import com.huatec.util.DateUtil;
import com.huatec.util.JsonDateValueProcessor;
import com.huatec.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

        @GetMapping("/list")
        public List<Post> list(Post post , HttpServletResponse response) throws  Exception{
            List<Post> postList = postService.findPosts();

            // 处理日期使之能在 easyUI 的 datagrid 中正常显示
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            // 将数据以 JSON 格式返回前端
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JSONArray.fromObject(postList, jsonConfig);
            result.put("rows", jsonArray);
            ResponseUtil.write(response, result);
            return null;
        }

        @PostMapping("/save")
        public String save(Post post, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
//           Admin admin = (Admin) session.getAttribute("currentAdmin");
////            System.out.println(admin.getUsername());
//            post.setAdmin(admin);
            post.setDate(DateUtil.getDate());
            int resultTotal = 0;
            String msg = "参数不合法";
            // 如果 id 为空，则添加公告，否则修改公告
            if (post.getId() == null){
                resultTotal = postService.addPost(post);
            }else{
                resultTotal = postService.updatePost(post);
            }
            JSONObject result = new JSONObject();
            if (resultTotal > 0) {
                result.put("code",200);
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg",msg);
            }
            ResponseUtil.write(response, result);
            return null;
        }
//
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids,
                         HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject result = new JSONObject();
        // 将要删除的公告的 id 进行处理
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            postService.deletePost(Integer.parseInt(idsStr[i]));

        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
   //根据ID查询其公告
    @RequestMapping("/getById")
    public String getById(@RequestParam(value = "id") Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
            Post post = postService.getPostById(id);
        // 处理日期使之能在 easyUI 的 datagrid 中正常显示
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        // 将数据以 JSON 格式返回前端
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(post, jsonConfig);
        result.put("rows", jsonArray);
        ResponseUtil.write(response, result);
            return null;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
