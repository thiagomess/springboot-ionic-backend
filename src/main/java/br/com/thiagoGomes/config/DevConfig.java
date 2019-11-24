package br.com.thiagoGomes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.thiagoGomes.service.DBService;
import br.com.thiagoGomes.service.EmailService;
import br.com.thiagoGomes.service.SmtpEmailService;

//COnfiguração do banco de dados de Test, que esta setado no application-teste.properties
@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	//recupera o valor da chave no application-dev.properties
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instatiateDatabase() {

		if(strategy.equalsIgnoreCase("create")){
			dbService.instantiateTestDatabase();
		}
		return true;
	}
	
	//Desse modo, quando injetar o email Service, usando o profile dev, irá criar a instancia do SmtpEmailService
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
