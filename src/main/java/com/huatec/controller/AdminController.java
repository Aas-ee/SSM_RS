package com.huatec.controller;


import com.huatec.domain.Admin;
import com.huatec.service.AdminService;
import com.huatec.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public String login(Admin admin, HttpServletRequest request, HttpSession session ) throws Exception {

        Admin resultAdmin =adminService.login(admin);
       // 密码错误或者用户名错误
        if(resultAdmin == null){
            request.setAttribute("admin",admin);
            request.setAttribute("errorMsg","please check your username or password");
//            return "login";
            return null;
        }else {
            //保存信息
            session.setAttribute("currentAdmin",resultAdmin);
//            System.out.println(resultAdmin.toString());
            session.setAttribute("username",resultAdmin.getUsername());
//            JSONObject result = new JSONObject();
//            result.put("code",200);
//            ResponseUtil.write(response ,result);
            return null;
        }
    }
//    //跳转至主页面请求
//    @RequestMapping("/main")
//    public String test(Model model) throws Exception{
//        return "home_page";
//    }
    @GetMapping("/list")
    public String list(Admin admin ,HttpServletResponse response)throws Exception{

        Map<String,Object> map =new HashMap<String,Object>();
        //判断查询条件是否为空，如果是，对条件做数据库模糊查询的处理
        if (admin.getUsername() != null
                && !"".equals(admin.getUsername().trim())) {
            map.put("username", "%" + admin.getUsername() + "%");
        }
        List<Admin> adminList= adminService.findAdmins(map);
        Integer total = adminService.getCount(map);

        //JSON类型返回数据
        JSONObject result = new JSONObject();
        JSONArray jsonArray =JSONArray.fromObject(adminList);
        result.put("rows",jsonArray);
        result.put("total",total);
        ResponseUtil.write(response ,result);
        return null;
    }
    @PostMapping("/save")
    public String save(Admin admin,HttpServletRequest request,HttpServletResponse response) throws Exception{

        int resultTotal =0;
        String msg = "参数不合法";
        if(admin.getId() ==null) {
            resultTotal = adminService.addAdmin(admin);
        }else {
            resultTotal = adminService.updateAdmin(admin);
        }
        JSONObject result = new JSONObject();
        if(resultTotal > 0){
            result.put("code",200);
            result.put("success",true);
        }else{
            result.put("success",false);
            result.put("msg",msg);
        }
        ResponseUtil.write(response ,result);
        return null;
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response,HttpSession session) throws Exception{

        JSONObject result = new JSONObject();
         //将要删除的管理员的id进行处理
        String[] idsStr=ids.split(",");
        for(int i=0; i< idsStr.length;i++){
            //不能删除超级管理员和当前登录的管理员
            if(idsStr[i].equals("1") && idsStr[i].equals(((Admin)session.getAttribute("currentAdmin")).getId().toString())){
                result.put("success" ,false);
                continue;
            }else {
                adminService.deleteAdmin(Integer.parseInt(idsStr[i]));
                result.put("success",true);
            }
        }
        ResponseUtil.write(response ,result);
        return null;

}
   @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        session.invalidate();
        return "abc";

   }

}
