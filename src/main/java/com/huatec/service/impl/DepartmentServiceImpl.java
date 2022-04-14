package com.huatec.service.impl;

import com.huatec.dao.DepartmentDao;
import com.huatec.domain.Department;
import com.huatec.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("DepartmentService")

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findDepartments(Map<String, Object> map) {

        return departmentDao.findDepartments(map);
    }

     @Override
    public Integer getCount(Map<String, Object> map) {

        return departmentDao.getCount(map);
    }
    @Override
    public Integer addDepartment(Department department) {

        return departmentDao.addDepartment(department);
    }

    @Override
    public Integer updateDepartment(Department department) {

        return departmentDao.updateDepartment(department);
    }
    @Override

    public Integer deleteDepartment(Integer id) {
        Integer flag = null;
        try {
            flag = departmentDao.deleteDepartment(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return flag;

    }
}
