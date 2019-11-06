package com.wf.info.controller;

import com.wf.info.bean.Email;
import com.wf.info.service.EmailService;
import com.wf.jobs.EmailJob;
import com.wf.sys.bean.Employee;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private JavaMailSenderImpl javaMailSender;
@Autowired
private EmailService emailService;
@RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Email email, HttpSession session) throws SchedulerException {


        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("job1", "group1").build();
    JobDataMap jobDataMap = job.getJobDataMap();
    jobDataMap.put("email",email);
    jobDataMap.put("javaMailSenderImpl",javaMailSender);


    //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
            jobDataMap.put("scheduler",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
       sched.scheduleJob(job, trigger);
        //启动定时任务管理器
        sched.start();
//保存到数据库一份
    Employee loginUser =(Employee)session.getAttribute("loginUser");
    Integer eid =loginUser.getEid();
    email.setEmpFk(eid);
    emailService.saveInfo(email);


        return "redirect:/message.jsp";
    }

}
