package br.com.thiagoGomes.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instanteDoPedido) {
		LocalDate dataAtualizada = instanteDoPedido.plusDays(7).toLocalDate();
		pagto.setDataVencimento(dataAtualizada);
		
	}

}
