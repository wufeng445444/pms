package com.wf.sys.service;

import com.wf.sys.bean.Role;
import com.wf.sys.bean.RoleExample;
import com.wf.sys.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
  @Resource
  private RoleMapper roleMapper;

    public int saveInfo(Role role) {
        roleMapper.saveInfo( role);

        return role.getRoleid();
    }

    public List<Role> getRoleList() {
        RoleExample example=new RoleExample();
        List<Role> roles = roleMapper.selectByExample(example);
        return  roles;
    }
}
