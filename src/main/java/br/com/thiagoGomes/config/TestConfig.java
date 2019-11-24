package br.com.thiagoGomes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.thiagoGomes.service.DBService;
import br.com.thiagoGomes.service.EmailService;
import br.com.thiagoGomes.service.MockEmailService;

//COnfiguração do banco de dados de Test, que esta setado no application-teste.properties
@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instatiateDatabase() {

		dbService.instantiateTestDatabase();

		return true;
	}

	//Desse modo, quando injetar o email Service, usando o profile Test, irá criar a instancia do MockEmailService
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
