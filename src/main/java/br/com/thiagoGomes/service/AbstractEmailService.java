package br.com.thiagoGomes.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.thiagoGomes.domain.Pedido;

//Utilizando o padrão Template Method, onde as classes reais irao extende-la
public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}O")
	private String sender;
	
	//Sobrescreve o metodo da interface EmailService
	@Override
	public void sendOrderConfirmationEMail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	//Define os dados que serão enviados
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());

		return sm;
	}

}
