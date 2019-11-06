package com.wf.sys.service;

import com.wf.sys.bean.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getManagers();

    Employee login(Employee employee);


    List<Employee> getOthers(Integer eid);

    int saveInfo(Employee employee);
}
