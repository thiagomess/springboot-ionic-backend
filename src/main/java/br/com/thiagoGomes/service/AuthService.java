package br.com.thiagoGomes.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Cliente;
import br.com.thiagoGomes.repositories.ClienteRepository;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPassEncoder;

	@Autowired
	EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não cadastrado");
		}

		String newPass = newPassword();
		cliente.setSenha(bCryptPassEncoder.encode(newPass));

		clienteRepository.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		//Usa a tabela unicode pra gerar digito, letra maiuscula e letra minuscula https://unicode-table.com/pt/
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48); //gera um numero de 0 a 9 e soma mais o elemento da tabela unicode entao da um numero de A a Z
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65); //gera um numero de 0 a 26 e soma mais o elemento da tabela unicode entao da um numero de a a z
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
