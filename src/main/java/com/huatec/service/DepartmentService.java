package com.huatec.service;


import com.huatec.domain.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {


    public List<Department> findDepartments(Map<String, Object> map);


    public Integer getCount(Map<String, Object> map);


    public Integer addDepartment(Department department);


    public Integer updateDepartment(Department department);


    public Integer deleteDepartment(Integer id);
}