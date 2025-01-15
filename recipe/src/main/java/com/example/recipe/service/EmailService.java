package com.example.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.recipe.base.BaseService;
	
@Service
public class EmailService extends BaseService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	public void sendEmail(String to, String subject, String body) {
		 SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
	}
}
