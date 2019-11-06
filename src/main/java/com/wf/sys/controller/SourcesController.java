package com.wf.sys.controller;

import com.wf.common.ResultEntity;
import com.wf.sys.bean.Sources;
import com.wf.sys.service.SourcesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/sources")
public class SourcesController {

    @Autowired
    private SourcesServices sourcesServices;
//    删
@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
@ResponseBody
public ResultEntity delete(Integer id){
    sourcesServices.delete(id);
    return ResultEntity.success();
}


//改
@RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Sources sources){
        sourcesServices.updateInfo( sources);
        return "redirect:/pm.jsp";
    }


//查

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){

//        获取顶级元素
        List<Sources> list=sourcesServices.getSourcesByPid(0);
//        获取顶级元素的子元素
        queryChildren(list.get(0));
        return list;
    }
    //    由顶级元素查二级菜单
//    完成递归查询
    private void queryChildren(Sources source) {
//        获得顶级菜单的id
        Integer id = source.getId();
//        根据顶级菜单的id查询到二级菜单
        List<Sources> sources = sourcesServices.getSourcesByPid(id);
//       递归
        for (Sources source1 : sources) {
            queryChildren(source1);
        }

//          将二级菜单放到顶级菜单里，作为他的一个属性
        source.setChildren(sources);
    }
//增
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)

    public String saveInfo(Sources source){
        sourcesServices.saveInfo(source);
        return "redirect:/pm.jsp";
    }
@RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public ModelAndView getSourcesInfo(@PathVariable("id") Integer sid){
        ModelAndView mv=new ModelAndView("pm-edit");
       Sources source =sourcesServices.getSourcesInfo(sid);
        mv.addObject( "onesource",source);
        return mv;
    }

}
