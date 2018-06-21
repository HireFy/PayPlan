package com.cobcap.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    private String host = "smtp.qq.com";
    private String port = "587";
    private String account = "2824908821@qq.com";
    private String password = "zywfhgxusmzhdfeb";
//
//    private String host = "smtp.gmail.com";
//    private String port = "587";
//    private String account = "hirefy.everglow@gmail.com";
//    private String password = "asd782003053asd";

    private Properties props;
    private Authenticator authenticator;
    private Session mailSession;
    private MimeMessage message;
    private InternetAddress from;

    public MailUtil() throws MessagingException {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.user", account);
        props.put("mail.password", password);

        // 构建授权信息，用于进行SMTP进行身份验证
        authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        // 使用环境属性和授权信息，创建邮件会话
        mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        message = new MimeMessage(mailSession);
        from = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(from);
    }

    public String getAndSendVerifyNumber(String to) throws MessagingException {
        // 设置收件人的邮箱
        InternetAddress internetAddress = new InternetAddress(to);
        this.message.setRecipient(Message.RecipientType.TO, internetAddress);

        // 设置邮件标题
        this.message.setSubject("验证码测试邮件");
        // 设置邮件的内容体
        StringBuffer numStr = new StringBuffer();
        int num;
        for (int i = 0; i < 6; i++) {
            // Math.random() 随机出0-1之间的实数，返回值是一个double 类型的
            num = (int) (Math.random() * 10);
            numStr.append(String.valueOf(num));
        }

        this.message.setContent(numStr.toString(), "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        //Transport.send(this.message);

        return numStr.toString();
    }


}
