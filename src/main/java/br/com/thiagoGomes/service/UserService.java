package br.com.thiagoGomes.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.thiagoGomes.security.UserSS;

/**
 *Classe que tem a responsabilidade de pegar e devolver o usuario logado
 */
public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
