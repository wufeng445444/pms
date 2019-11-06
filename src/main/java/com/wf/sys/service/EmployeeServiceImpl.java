package com.wf.sys.service;

import com.wf.sys.bean.Employee;
import com.wf.sys.bean.EmployeeExample;
import com.wf.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EmployeeServiceImpl  implements  EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> getManagers() {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;

    }

    public Employee login(Employee employee) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria=example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees=employeeMapper.selectByExample(example);
        if (employees !=null&& employees.size()>0){
            employee=employees.get(0);
            return employee;
        }
        return null;


    }

    public List<Employee> getOthers(Integer eid) {

        return  employeeMapper.getOthers(eid);
    }

    public int saveInfo(Employee employee) {
        employeeMapper.insertEmp(employee);
        return employee.getEid();
    }
}
