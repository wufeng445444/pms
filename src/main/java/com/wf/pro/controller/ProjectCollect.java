package com.wf.pro.controller;


import com.wf.pro.bean.Project;
import com.wf.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*新操作一个表或者新操作一个实体类，就新建一个controller*/
@Controller
@RequestMapping(value = "/pro")
public class ProjectCollect {
    @Autowired
    private ProjectService projectService;
    /*要求project的属性值要与页面上的属性值保持一致*/
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/project-base.jsp";
    }
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectJsonList(){
        return projectService.getProjectJsonList();
    }

    /*项目管理里的带条件的多表查询*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getProjectList(Integer cid,String keyword,Integer orderby){
        ModelAndView mv=new ModelAndView("project-base");
        List<Project> list=projectService.getProjectList(cid,keyword,orderby);
       mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value ="/withAnalyseJsonList",method =RequestMethod.GET )
    @ResponseBody
    public List<Project> getProjectWithAnalyseJsonList(){
        return projectService.getProjectWithAnalyseJsonList();

    }


}
