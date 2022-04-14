package com.huatec.dao;


import com.huatec.domain.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {

    //登录
    public Admin login(Admin admin);

    //根据条件查询管理员
    public List<Admin> findAdmins(Map<String,Object> map);

    //根据条件查询管理员人数
    public Integer getCount(Map<String,Object> map);

    //增加管理人员
    public Integer addAdmin(Admin admin);

    //修改管理人员
    public Integer updateAdmin(Admin admin);

    //删除管理人员
    public Integer deleteAdmin(Integer id);

}
