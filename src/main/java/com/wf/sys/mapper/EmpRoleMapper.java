package com.wf.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface EmpRoleMapper {
    void insert(@Param("eid") int empid,@Param("rid") int roleid);
}
