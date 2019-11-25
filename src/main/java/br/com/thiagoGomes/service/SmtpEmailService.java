package br.com.thiagoGomes.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

//Utiliza-se o padrão Template Method= aqui recupera o template criado e implementa os metodos que faltam
public class SmtpEmailService extends AbstractEmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

	//A Interface mailSender, já consegue recuperar todas as configurações do email que estão no application-dev.properties
	@Autowired
	private MailSender mailSender;
	
	//Interface que irá abstrair as configurações do application-dev.properties e enviar o email com HTML
	@Autowired
	private JavaMailSender JavaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Enviando Email....");
		
		mailSender.send(msg);
		
		LOGGER.info("Email enviado!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOGGER.info("Enviando Email HTML....");
		
		JavaMailSender.send(msg);
		
		LOGGER.info("Email enviado!");
		
	}

}
