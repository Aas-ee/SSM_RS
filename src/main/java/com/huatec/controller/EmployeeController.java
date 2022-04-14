package com.huatec.controller;

import com.huatec.domain.Employee;
import com.huatec.domain.Post;
import com.huatec.service.EmployeeService;
import com.huatec.util.IntegrateObject;
import com.huatec.util.JsonDateValueProcessor;
import com.huatec.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

@RestController
@CrossOrigin
@RequestMapping("/empl")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String list(Employee employee, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        //根剧条件查询
        if (employee.getId() != null && !"".equals(employee.getId().trim())) {
            map.put("id", employee.getId());
        }
            if (employee.getName() != null && !"".equals(employee.getName().trim())) {
            map.put("name",employee.getName());
            }
            if(employee.getSex() !=null && !"".equals(employee.getSex().trim())){
                map.put("sex",employee.getSex());
            }
            if(employee.getDepartment() !=null){
                if(employee.getDepartment().getName() !=null && !"".equals(employee.getDepartment().getName().trim())){
                    map.put("department_name","%" + employee.getDepartment().getName() + "%");
                }
            }
            if(employee.getPosition() !=null){
                if(employee.getPosition().getName() !=null && !"".equals(employee.getPosition().getName().trim())){
                    map.put("position_name","%" + employee.getPosition().getName() + "%");
                }
            }
         List<Post> postList = employeeService.findEmployees(map);
            Integer total = employeeService.getCount(map);

        // 处理日期使之能在 easyUI 的 datagrid 中正常显示
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,
                new JsonDateValueProcessor());

        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(postList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response,result);
        return null;
    }



    @PostMapping ("/save")
    public String save(@RequestParam("dept_id") Integer dept_id, @RequestParam("pos_id") Integer pos_id,@RequestParam("state_name") String state_name, @RequestParam("updateFlag") String updateFlag,Employee employee, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws  Exception{

        int  resultTotal = 0;
         String msg = "参数不合法！";
        //完成Depatement 和position 在employee中的关联映射
        IntegrateObject.genericAssociation(dept_id,pos_id,state_name,employee);

        JSONObject result = new JSONObject();

        if(updateFlag.equals("no")){
          try {
              resultTotal =employeeService.addEmployee(employee);
            if(resultTotal > 0){
                result.put("code",200);
                result.put("success",true);
            }else {
                result.put("success",false);
                result.put("msg",msg);
            }
          }catch (Exception e){
              result.put("success",false);
              result.put("msg",e.getMessage());
          }
        }else if(updateFlag.equals("yes")){
                resultTotal = employeeService.updateEmployee(employee);
                if(resultTotal > 0){
                    result.put("code",200);
                    result.put("success",true);
                }else {
                    result.put("success",false);
                    result.put("msg",msg);
                }
        }
        ResponseUtil.write(response,result);
     return null;
    }
    @PostMapping("/delete")
    //value = "ids"
    public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response,HttpSession session)throws
            Exception{
         JSONObject result = new JSONObject();

         String[] idsStr = ids.split(",");
         for(int i=0; i< idsStr.length;i ++){
             employeeService.deleteEmployee(idsStr[i]);
         }
         result.put("code",200);
         result.put("success",true);
         ResponseUtil.write(response,result);
         return null;
    }
    //springmvc 日期绑定
    @InitBinder
    public void initBinder(WebDataBinder binder) {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @GetMapping("/edu")
    public List<String> findEdu(HttpServletResponse response) throws Exception {
        List<String> list = employeeService.findEdu();
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(list);
        result.put("rows", jsonArray);
        ResponseUtil.write(response,result);
       return null;
    }


}

