package com.wf.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleSourceMapper {
    void insert(@Param("rid") int roleid,@Param("sid") int parseInt);
}
