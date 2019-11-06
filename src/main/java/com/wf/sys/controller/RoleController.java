package com.wf.sys.controller;

import com.wf.common.ResultEntity;
import com.wf.sys.bean.Role;
import com.wf.sys.service.RoleService;
import com.wf.sys.service.RoleSourceService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleSourceService roleSourceService;

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoleList(){
        return  roleService.getRoleList();

    }
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){
//        1,向角色表中添加表数据，而且必须返回该角色在数据表中的id
        int roleid=roleService.saveInfo(role);
//          2,将角色和权限的对应关系添加到中间表中
        roleSourceService.saveInfo(roleid,ids);

        return ResultEntity.success();
    }
}
