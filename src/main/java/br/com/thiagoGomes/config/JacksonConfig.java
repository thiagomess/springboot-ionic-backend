package br.com.thiagoGomes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.thiagoGomes.domain.PagamentoComBoleto;
import br.com.thiagoGomes.domain.PagamentoComCartao;

//classe de configuração com um método @Bean para registrar as subclasses da casse Pagamento
@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class); //Mapeia as classes
				objectMapper.registerSubtypes(PagamentoComBoleto.class);//Mapeia as classes
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}