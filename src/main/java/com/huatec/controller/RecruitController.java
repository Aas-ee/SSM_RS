package com.huatec.controller;

import com.huatec.domain.Admin;
import com.huatec.domain.Recruit;
import com.huatec.service.RecruitService;
import com.huatec.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2022-04-12 14:11
 */
@Controller
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @GetMapping("/list")
    public String list(Recruit recruit, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果查询条件为空 则对数据库进行模糊查询处理
        if (recruit.getTitle() != null && !"".equals(recruit.getTitle().trim())){
            map.put("title","%" + recruit.getTitle() +"%");
        }
        List<Recruit> recruitList = recruitService.findRecruits(map);
        Integer total = recruitService.getCount(map);


        // 处理日期使之能在 easyUI 的 datagrid 中正常显示
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,
                new JsDateJsonValueProcessor());
        // 将数据以 JSON 格式返回前端
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(recruitList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

//    @PostMapping("/save")
//    public String save(Recruit recruit, HttpServletRequest request, HttpServletResponse response) throws Exception{
//
//    }
}
