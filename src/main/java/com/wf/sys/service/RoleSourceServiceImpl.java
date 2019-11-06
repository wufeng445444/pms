package com.wf.sys.service;

import com.wf.sys.mapper.RoleSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleSourceServiceImpl implements  RoleSourceService {

    @Autowired
    private RoleSourceMapper roleSourceMapper;

    public void saveInfo(int roleid, String ids) {
        String[] idArr=ids.split(",");
        for (String s:idArr){
            roleSourceMapper.insert(roleid,Integer.parseInt(s));
        }
    }
}
