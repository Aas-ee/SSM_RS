package com.huatec.controller;
import com.huatec.domain.Position;
import com.huatec.service.PositionService;
import com.huatec.util.ResponseUtil;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/list")
    public String list(Position position, HttpServletResponse response)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();

        //条件查询
        if(position.getName() != null && !"".equals(position.getName().trim())){
            map.put("name", "%" + position.getName() + "%");
        }
        List<Position> positionList =positionService.findPositions(map);
        Integer total = positionService.getCount(map);
        JSONObject result = new JSONObject();
        JSONArray  jsonArray=JSONArray.fromObject(positionList);
        result.put("rows",jsonArray);
        result.put("total",total);
        ResponseUtil.write(response ,result);
        return null;
    }

    @PostMapping("/save")
    public String save(@RequestParam("updateFlag")String updateFlag ,
                       Position position,
                       HttpServletRequest request,
                       HttpServletResponse response)
    throws  Exception{
        int resultTotal = 0;
        String msg = "参数不合法";
        if(updateFlag.equals("no") ){
            resultTotal =positionService.addPosition(position);
        }else if(updateFlag.equals("yes")) {
            resultTotal = positionService.updatePosition(position);
        }
        JSONObject result = new JSONObject();
        if(resultTotal >0 ){
            result.put("code",200);
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("msg",msg);
        }
     ResponseUtil.write(response ,result);
        return null;
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids,HttpServletResponse response)
    throws  Exception{
        JSONObject result = new JSONObject();
        String[] idsStr =ids.split(",");
        for(int i = 0; i<idsStr.length; i++){
            try {
                positionService.deletePosition(Integer.parseInt(idsStr[i]));
            result.put("success",true);
            }catch (Exception e){
                result.put("success",false);
            }
        }
        ResponseUtil.write(response ,result);
     return null;
    }
    //**处理获得职位 id 与 name 请求，用于前端 easyUI combobox 的显示
    @RequestMapping("/getcombobox")
    @ResponseBody
    public JSONArray getPos(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Position> posList = positionService.findPositions(map);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for (Position pos : posList) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("id", pos.getId());
            result.put("name", pos.getName());
            list.add(result);
        }
        // 返回 JSON
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray;
    }

}
