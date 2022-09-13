package net.softsociety.issho.manager.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Service
public class MailSenderService  {

	private final JavaMailSender mailSender;

	// properties 파일내용을 하기 변수에 주입
	@Value(value = "${spring.mail.username}")
	public String from;

	public void mailSend(String mailTo,String domain) throws Exception {
		MimeMessage m = mailSender.createMimeMessage();
		MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
		h.setFrom(from);
		h.setTo(mailTo);
		h.setSubject(domain +" 프로젝트에 초대합니다.");
		h.setText("http://localhost:/9990/issho/project/main/"+ domain);
		mailSender.send(m);
	}

	

}