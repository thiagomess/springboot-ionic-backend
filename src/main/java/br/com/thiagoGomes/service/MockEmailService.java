package br.com.thiagoGomes.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

//Utiliza-se o padrão Template Method= aqui recupera o template criado e implementa os metodos que faltam
public class MockEmailService extends AbstractEmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Simulando envio de Email....");
		LOGGER.info(msg.toString());
		LOGGER.info("Email enviado!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOGGER.info("Simulando envio de HTML....");
		LOGGER.info(msg.toString());
		LOGGER.info("Email enviado!");

	}

}
