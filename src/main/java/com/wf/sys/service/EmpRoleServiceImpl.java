package com.wf.sys.service;

import com.wf.sys.bean.Employee;
import com.wf.sys.mapper.EmpRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmpRoleServiceImpl implements  EmpRoleService {
    @Resource
    private EmpRoleMapper empRoleMapper;


    public void insert(int empid, String[] roleids) {
        for (String roleid :roleids){
            empRoleMapper.insert(empid,Integer.parseInt(roleid));
        }

    }
}
