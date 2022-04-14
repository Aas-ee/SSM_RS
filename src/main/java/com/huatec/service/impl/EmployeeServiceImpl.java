package com.huatec.service.impl;

import com.huatec.dao.EmployeeDao;
import com.huatec.domain.Employee;
import com.huatec.domain.Post;
import com.huatec.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("EmployService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Post> findEmployees(Map<String, Object> map) {

        return employeeDao.findEmployees(map);
    }
     @Override
    public Integer getCount(Map<String, Object> map) {

        return employeeDao.getCount(map);
    }
    @Override
    public Integer addEmployee(Employee employee) {
        Integer flag = null;
        try {
            //如果插入主键重复，抛出异常
            flag =  employeeDao.addEmployee(employee);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return flag;
    }

    @Override
    public Integer updateEmployee(Employee employee) {

        return employeeDao.updateEmployee(employee);
    }
    @Override
    public Integer deleteEmployee(String id) {

        return employeeDao.deleteEmployee(id);
    }

    @Override
    public List<String> findEdu() {
        return employeeDao.findEdu();
    }


}



