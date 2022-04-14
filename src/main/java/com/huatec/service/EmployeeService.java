package com.huatec.service;


import java.util.List;
import java.util.Map;

import com.huatec.domain.Employee;
import com.huatec.domain.Post;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface EmployeeService {


    public List<Post>findEmployees(Map<String, Object> map);


    public Integer getCount(Map<String, Object> map);


    public Integer addEmployee(Employee employee);


    public Integer updateEmployee(Employee employee);


    public Integer deleteEmployee(String id);

    public List<String> findEdu();

}