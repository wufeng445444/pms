package com.wf.sys.mapper;


import java.util.List;

import com.wf.sys.bean.Employee;
import com.wf.sys.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer eid);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer eid);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);


    List<Employee> getOthers(Integer eid);

    void insertEmp(Employee employee);
}