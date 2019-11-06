package com.wf.pro.service;


import com.wf.pro.bean.Project;

import java.util.List;

public interface ProjectService {

    void saveInfo(Project project);


    List<Project> getProjectJsonList();

    List<Project> getProjectList(Integer cid, String keyword, Integer orderby);

    List<Project> getProjectWithAnalyseJsonList();
}
