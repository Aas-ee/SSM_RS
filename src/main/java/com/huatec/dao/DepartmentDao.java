package com.huatec.dao;

import java.util.List;
import java.util.Map;

import com.huatec.domain.Department;
import org.springframework.stereotype.Repository;



@Repository
public interface DepartmentDao {


       public List<Department> findDepartments(Map<String, Object> map);


    public Integer getCount(Map<String, Object> map);


    public Integer addDepartment(Department department);


    public Integer updateDepartment(Department department);


    public Integer deleteDepartment(Integer id);

}