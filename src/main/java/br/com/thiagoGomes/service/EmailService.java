package br.com.thiagoGomes.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.thiagoGomes.domain.Pedido;

//Esta sendo utilizado o padrão Strategy
public interface EmailService {

	void sendOrderConfirmationEMail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
