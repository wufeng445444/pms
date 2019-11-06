package com.wf.pro.controller;

import com.wf.pro.bean.Attachment;
import com.wf.pro.bean.Project;
import com.wf.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/attach")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveInfo(Attachment atta, MultipartFile attachment, HttpSession session){
//FASTDFS 分布式的文件服务器，可以无限拓展服务器大小
        /*1,上传文件，获取绝对路径*/
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        File file=new File(realPath);
//        2，判断文件夹是否存在
        if (!file.exists()){
            file.mkdirs();
        }
//3，避免重名，获取文件名称
        String originalFilename = attachment.getOriginalFilename();

        String realName= UUID.randomUUID().toString().replaceAll("-","")+originalFilename;
//        将文件传到对应的文件下
        try {
            attachment.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        atta.setPath(realPath+"/"+realName);
        attachmentService.saveInfo(atta);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("statusCode",200);
        map.put("message","保存成功");
        return map;
    }
}
