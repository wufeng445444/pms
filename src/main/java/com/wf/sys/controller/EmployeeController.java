package com.wf.sys.controller;

import com.wf.sys.bean.Employee;
import com.wf.sys.bean.Sources;
import com.wf.sys.service.EmpRoleService;
import com.wf.sys.service.EmployeeService;
import com.wf.sys.service.SourcesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpRoleService empRoleService;
    @Autowired
    private SourcesServices sourcesServices;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee,String[] roleids){
//               1，员工表中添加数据
                int empid=employeeService.saveInfo(employee);
//                2，往员工和角色关联表添加数据
                empRoleService.insert(empid,roleids);
                return "redirect:/user.jsp";
    }



    @RequestMapping(value = "/manages" ,method= RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers(){
      return  employeeService.getManagers();
    }
/*登入*/
    @RequestMapping(value = "/login",method =RequestMethod.GET)
    public  String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String validateCode=(String) session.getAttribute("validateCode");
      if (code.equalsIgnoreCase(validateCode)){
            session.removeAttribute("validateCode");
           employee=employeeService.login(employee);
           if (employee!=null){
               Integer eid = employee.getEid();
             List<Sources> list=  sourcesServices.getSourcesListByEid(eid);
                session.setAttribute("sourcesList",list);
               session.setAttribute("loginUser",employee);
               return "redirect:/index.jsp";
           }
           else {
               attributes.addFlashAttribute("errMag","用户名或者密码错误");
               return "redirect:/login";
           }
      }
        attributes.addFlashAttribute("errMag","验证码错误");
            return "redirect:/login";
    }

    /*登出*/
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
public String logout(HttpSession session){


    session.invalidate();
    return "redirect:/login.jsp";
}

//发邮件
    @ResponseBody
 @RequestMapping(value = "/others",method =RequestMethod.GET )
    public  List<Employee> getOthers(HttpSession session){
     Employee loginUser=(Employee)session.getAttribute("loginUser");
     Integer eid = loginUser.getEid();
     List<Employee> emps= employeeService.getOthers(eid);
            return  emps;

 }


}
