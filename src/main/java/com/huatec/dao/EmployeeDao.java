package com.huatec.dao;


import java.util.List;
import java.util.Map;

import com.huatec.domain.Employee;

import com.huatec.domain.Post;
import org.springframework.stereotype.Repository;




@Repository
public interface EmployeeDao {


    public List<Post>findEmployees(Map<String, Object> map);


    public Integer getCount(Map<String, Object> map);

    public Integer addEmployee(Employee employee);

    public Integer updateEmployee(Employee employee);


    public Integer deleteEmployee(String id);

    public List<String> findEdu();

}