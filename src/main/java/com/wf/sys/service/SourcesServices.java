package com.wf.sys.service;

import com.wf.sys.bean.Sources;

import java.util.List;

public interface SourcesServices {

    List<Sources> getSourcesByPid(int i);

    void saveInfo(Sources source);

    Sources getSourcesInfo(Integer sid);

    void updateInfo(Sources sources);

    void delete(Integer id);

    List<Sources> getSourcesListByEid(Integer eid);
}
