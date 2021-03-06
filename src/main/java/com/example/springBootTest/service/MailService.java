package com.example.springBootTest.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *   
 * @date 2017年9月8日
 */
@Component
public class MailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 邮件模板文件路径
    private static String templatePath = "/mail_template";

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 简单的文本发送
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
    
    /**
     * 邮件内容套用html样式
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            // 添加多个附件可以使用多条 helper.addAttachment(fileName, file)
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送邮件内容内嵌静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送模板邮件
     * 
     * @param to 收件人地址
     * @param subject 邮件主题
     * @param map 邮件内容与模板内容转换对象
     * @param templateName 模板文件名称
     * @throws IOException
     * @throws TemplateException
     */
    @SuppressWarnings("deprecation")
	public void sendTemplateMail(String to, String subject, 
			String templateName, Map<String, String> map) throws IOException, TemplateException {
    	Configuration conf = new Configuration();
        // 设置模板文件路径
        conf.setClassForTemplateLoading(MailService.class, templatePath);
    	
        Writer out = new StringWriter(2048);
        Template temp = conf.getTemplate(templateName);
        temp.setEncoding("UTF-8");
        temp.process(map, out);
        String content = out.toString();
        
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("模板邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送模板邮件时发生异常！", e);
        }
    }
}
