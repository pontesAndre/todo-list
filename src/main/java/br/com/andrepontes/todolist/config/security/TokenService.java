package br.com.andrepontes.todolist.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.andrepontes.todolist.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${todo.jwt.expiration}")
	private String expiration;

	@Value("${todo.jwt.secret}")
	private String secret;

	public String createToken(Authentication authentication) {
		User loginUser = (User) authentication.getPrincipal();
		Date today = new Date();
		Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("API TODO-LIST").setSubject(loginUser.getId().toString()).setIssuedAt(today)
				.setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getIdUser(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());

	}

}
