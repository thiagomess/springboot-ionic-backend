package br.com.thiagoGomes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.thiagoGomes.service.EmailService;
import br.com.thiagoGomes.service.SmtpEmailService;

//Configuração do banco de dados de prod, que esta setado no application-teste.properties
@Configuration
@Profile("prod")
public class ProdConfig {

	//Desse modo, quando injetar o email Service, usando o profile prod, irá criar a instancia do SmtpEmailService
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
