package com.wf.sys.service;

import com.wf.sys.bean.Role;

import java.util.List;

public interface RoleService {
    int saveInfo(Role role);

    List<Role> getRoleList();
}
