package com.wf.sys.controller;

import com.wf.sys.bean.Position;
import com.wf.sys.bean.Role;
import com.wf.sys.service.PositionService;
import com.wf.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pos")
public class PositionContrlller {
    @Autowired
    private PositionService positionService;






    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
        public List<Position> getPositionList(){
        List<Position> list= positionService. getPositionList();
                return list;

        }
    }
