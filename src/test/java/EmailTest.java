import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailTest {
@Test
    public void test01() throws MessagingException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
//        邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");

        helper.setFrom("wf2501936780@163.com");
        helper.setTo("xhn121397442@163.com");
//        邮件主题
        helper.setSubject("这是邢昊楠的对象的邮件测试");
//        内容
        helper.setText("<html><head></head><body><h1><span></span>我是邢昊楠的爸爸</h1><img src=cid:identifie/>嘿嘿嘿</body></html>",true);
//        作为附件下载
    FileSystemResource file=new FileSystemResource(new File("D:\\Desktop\\god.jpg"));
//    CoolImage.jpg附件的名称，file附件的文件，必须要有后缀，不然浏览器都不知道怎么解析
    helper.addAttachment("CoolImage.jpg",file);
//图片作为内置资源
    FileSystemResource file1=new FileSystemResource(new File("D:\\Desktop\\pu.jpg"));
    helper.addInline("identifie",file1);


//        发送邮箱
        bean.send(mimeMessage);
        System.out.println("邮件发送完成");
//        带有附件的文件

    }
}
