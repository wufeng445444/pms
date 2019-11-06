package com.wf.pro.service;



import com.wf.pro.bean.Project;
import com.wf.pro.bean.ProjectExample;
import com.wf.pro.mapper.ProjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl  implements  ProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    public void saveInfo(Project project){
        projectMapper.insert(project);
    }

    public List<Project> getProjectJsonList() {
        ProjectExample example=new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        return projects;

    }

    public List<Project> getProjectList(Integer cid, String keyword, Integer orderby) {
            return  projectMapper.getProjectListWithSearch(cid,keyword,orderby);
    }

    public List<Project> getProjectWithAnalyseJsonList() {
        return  projectMapper.getProjectWithAnalyseJsonList();
    }


}
