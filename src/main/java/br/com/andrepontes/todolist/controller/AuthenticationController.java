package br.com.andrepontes.todolist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrepontes.todolist.config.security.TokenService;
import br.com.andrepontes.todolist.controller.dto.TokenDto;
import br.com.andrepontes.todolist.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManeger;
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken login = form.process();
		try {
			Authentication authentication = authManeger.authenticate(login);
			String token = tokenService.createToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
