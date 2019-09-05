package br.com.andrepontes.todolist.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.andrepontes.todolist.model.User;
import br.com.andrepontes.todolist.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private static final int BEARER_SPACE = 7;

	private TokenService tokenService;

	private UserRepository userRepository;

	public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = retriveToken(request);
		boolean valid = tokenService.isTokenValid(token);
		if (valid) {
			authClient(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authClient(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = userRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String retriveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return (token == null || token.isEmpty() || !token.startsWith("Bearer ")) ? null
				: token.substring(BEARER_SPACE, token.length());
	}

}
