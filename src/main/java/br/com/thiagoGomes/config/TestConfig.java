package br.com.thiagoGomes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.thiagoGomes.service.DBService;

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
}
