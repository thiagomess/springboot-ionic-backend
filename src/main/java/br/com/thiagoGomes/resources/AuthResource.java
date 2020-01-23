package br.com.thiagoGomes.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagoGomes.dto.EmailDTO;
import br.com.thiagoGomes.security.JWTUtil;
import br.com.thiagoGomes.security.UserSS;
import br.com.thiagoGomes.service.AuthService;
import br.com.thiagoGomes.service.UserService;
import io.swagger.annotations.ApiOperation;


/**
 * Classe responsavel por gerar um novo token, desde que o usuario ainda esteja com seu token valido
 */

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;

	 @ApiOperation(value="Atualiza token")
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	 @ApiOperation(value="Reseta senha")
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		authService.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}


}
