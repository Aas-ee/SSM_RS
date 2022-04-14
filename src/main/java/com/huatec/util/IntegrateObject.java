package com.huatec.util;

import com.huatec.domain.Department;
import com.huatec.domain.Employee;
import com.huatec.domain.Position;
import com.huatec.domain.State;

public class IntegrateObject {
    /**
     * 由于部门和职位在 Employee 中是对象关联映射，
     * 所以不能直接接收参数，需要创建 Department 对象和 Position 对象
     * */
    public static void genericAssociation(Integer dept_id, Integer pos_id,String state_name, Employee employee){
       Department department = new Department();
        department.setId(dept_id);
        Position position = new Position();
        position.setId(pos_id);
        State state = new State();
        state.setName(state_name);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setState(state);
    }
}

