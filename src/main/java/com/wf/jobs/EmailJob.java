package com.wf.jobs;

import com.wf.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap=jobExecutionContext.getMergedJobDataMap();
        Email email=(Email) dataMap.get("email");
        JavaMailSenderImpl javaMailSender= (JavaMailSenderImpl) dataMap.get("javaMailSenderImpl");
        Scheduler sched= (Scheduler) dataMap.get("scheduler");
       try{
           //        邮件对象
           MimeMessage mimeMessage =javaMailSender.createMimeMessage();
           MimeMessageHelper helper= null;
           try {
               helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
           } catch (MessagingException e) {
               e.printStackTrace();
           }

           helper.setFrom("wf2501936780@163.com");
           helper.setTo(email.getEname());
//        邮件主题
           helper.setSubject(email.getTitle());
//        内容
           helper.setText(email.getContent(),true);
//        作为附件下载
           FileSystemResource file=new FileSystemResource(new File("D:\\Desktop\\god.jpg"));
//    CoolImage.jpg附件的名称，file附件的文件，必须要有后缀，不然浏览器都不知道怎么解析
           helper.addAttachment("CoolImage.jpg",file);
//图片作为内置资源
           FileSystemResource file1=new FileSystemResource(new File("D:\\Desktop\\pu.jpg"));
           helper.addInline("identifie",file1);


//        发送邮箱
           javaMailSender.send(mimeMessage);
           System.out.println("邮件发送完成");

           System.out.println("定时发送邮件");
           sched.shutdown(true);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
        System.out.println("发送定时邮箱");
    }
}
