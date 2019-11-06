package com.wf.usual.controller;

import com.wf.sys.bean.Employee;
import com.wf.usual.bean.TieZi;
import com.wf.usual.service.TieZiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/tz")
public class TieZiController {
    @Autowired
    private TieZiService tieZiService;


@RequestMapping(value="/saveInfo",method= RequestMethod.POST)
public String saveInfo(TieZi tieZi,HttpSession  session){
   Employee employee = (Employee)session.getAttribute("loginUser");
    Integer eid = employee.getEid();
    tieZi.setEmpFk(eid);
    tieZi.setAddDate(new Date());
    tieZiService.saveInfo(tieZi);
    return "redirect:/main.jsp";
 }
}
