package com.huatec.controller;
import com.huatec.domain.Department;
import com.huatec.service.DepartmentService;
import com.huatec.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
@RequestMapping("/dept")
public class DeptController {

    @Autowired

    private DepartmentService departmentService;

    @GetMapping("/list")
    public String list(Department department, HttpServletResponse response)throws Exception{

        Map<String,Object> map = new HashMap<String,Object>();
        //判断查询条件是否为空，如果是，对条件做数据库模糊查询的处理
        if (department.getName() != null
                && !"".equals(department.getName().trim())) {
            map.put("name", "%" + department.getName() + "%");
        }
        List<Department> deptList = departmentService.findDepartments(map);
       Integer total = departmentService.getCount(map);

       //JSON类型的返回数据
       JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(deptList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }
    @PostMapping("/save")
    public String save(Department department, HttpServletRequest request,HttpServletResponse response) throws Exception{
        int resultTotal = 0;
        String msg = "参数不合法";
        // 如果 id 为空，则添加部门，否则修改部门
        if (department.getId() == null)
            resultTotal = departmentService.addDepartment(department);
        else
            resultTotal = departmentService.updateDepartment(department);
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
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids,
                         HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        // 将要删除的部门的 id 进行处理
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            // 捕获 service 层抛出的异常，如果捕获到则置 success 值为 false，返回给前端
            try {
                departmentService.deleteDepartment(Integer.parseInt(idsStr[i]));
                result.put("code",200);
                result.put("success", true);
            } catch (Exception e) {
                result.put("success", false);
                result.put("error",e.getMessage());
            }
        }
        ResponseUtil.write(response, result);
        return null;
    }

    //处理获得部门 id 与 name 请求，用于前端 easyUI combobox 的显示

    @RequestMapping("/getcombobox")
    @ResponseBody
    public  JSONArray getDept(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Department> deptList = departmentService.findDepartments(map);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (Department dept : deptList){
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("id",dept.getId());
            result.put("name",dept.getName());
            list.add(result);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray;
    }





}



