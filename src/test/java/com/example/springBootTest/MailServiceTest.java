package com.example.springBootTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springBootTest.service.MailService;

/**
 *   
 * @date 2017年9月8日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

//    @Test
//    public void testSimpleMail() throws Exception {
//        mailService.sendSimpleMail("wellney126@126.com", "test simple mail", " hello this is simple mail");
//    }
    
//    @Test
//    public void testHtmlMail() throws Exception {
//        String content="<html>\n" +
//                "<body>\n" +
//                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
//                "</body>\n" +
//                "</html>";
//        mailService.sendHtmlMail("wellney126@126.com", "test html mail", content);
//    }
    
//    @Test
//    public void sendAttachmentsMail() {
//        String filePath="C:\\Users\\Administrator\\Desktop\\carry6字段对应关系.txt";
//        mailService.sendAttachmentsMail("wellney126@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
//    }
    
//    @Test
//    public void sendInlineResourceMail() {
//        String rscId = "sample006";
//        String content="<html><body>这是邮件内容内嵌有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
//        String imgPath = "E:\\蒲公英.jpg";
//
//        mailService.sendInlineResourceMail("wellney126@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
//    }
    
  @Test
  public void testTemplateMail() throws Exception {
      Map<String, String> variableMap = new HashMap<String, String>();
      variableMap.put("username", "Wellney126");
      mailService.sendTemplateMail("wellney126@126.com", "主题：这是模板邮件", "template_1.ftl", variableMap);
  }
}
