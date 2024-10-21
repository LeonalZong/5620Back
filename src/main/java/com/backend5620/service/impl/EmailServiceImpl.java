package com.backend5620.service.impl;

import com.backend5620.mapper.UserMapper;
import com.backend5620.object.Users;
import com.backend5620.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserMapper userMapper;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendAbnormalDataNotification(int userId) {
        Users user = userMapper.getById(userId);
        if (user != null && user.getEmail() != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("lyonzong_sen@qq.com");
            message.setTo(user.getEmail());
            message.setSubject("Abnormal data notification");
            message.setText("Abnormal data notification");
            mailSender.send(message);
        }
    }
}
