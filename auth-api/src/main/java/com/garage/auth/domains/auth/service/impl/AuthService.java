package com.garage.auth.domains.auth.service.impl;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.garage.auth.config.security.TokenService;
import com.garage.auth.domains.auth.gateway.IAuthGateway;
import com.garage.auth.domains.auth.models.Tenant;
import com.garage.auth.domains.auth.models.Usuario;
import com.garage.auth.domains.auth.service.IAuthService;
import com.garage.auth.exceptions.BusinessException;
import com.garage.auth.exceptions.NotFoundException;
import com.garage.auth.infraestructure.api.auth.dtos.RequestRefreshPasswordDto;
import com.garage.auth.infraestructure.api.auth.dtos.TokenDto;
import com.garage.auth.infraestructure.api.auth.dtos.UserLoginRequestDto;

@Component
public class AuthService implements IAuthService {

	private static final String EMPRESA_ESTA_NULO = "O campo empresa esta nulo";
	private static final Exception TOKEN_INVALIDO = null;

	private IAuthGateway authGateway;
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;
	private PasswordEncoder passwordEncoder;

	public AuthService(IAuthGateway authGateway,
			AuthenticationManager authenticationManager,
			TokenService tokenService,
			PasswordEncoder passwordEncoder) {

		this.authGateway = authGateway;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public TokenDto auth(UserLoginRequestDto dto) {
		
		var login = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
		var authenticate = authenticationManager.authenticate(login);
		String token = tokenService.buildToken(authenticate);
		Usuario usuario = authGateway.buscaUsuarioPorEmail(dto.email());
		TokenDto tokenDto = new TokenDto(token, "Bearer", null);
		if (usuario.getTenant() != null) {
			tokenDto.atualizaNomeEmpresa(usuario.getTenant().getNome());
		}
		usuario.atualizaDataUltimoLogin();
		authGateway.salvarUsuario(usuario);
		return tokenDto;
	}

	@Override
	public void updatePasswordByRefreshToken(RequestRefreshPasswordDto dto) throws BusinessException {
		var entity = authGateway.buscaUsuarioPorEmail(dto.email());

		if (!entity.getTokenRefreshPassword().equals(dto.tokenRefreshPassword())
				|| !entity.isTokenRefreshPasswordValid()) {
			throw new BusinessException(TOKEN_INVALIDO);
		}
		validPasswordPolicies(dto.newPassword());
		entity.alteraPassword(passwordEncoder.encode(dto.newPassword()));
		authGateway.salvarUsuario(entity);
	}

	@Override
	public Usuario getUSerLogged() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		var principal = (UserDetails) authentication.getPrincipal();

		return authGateway.buscaUsuarioPorEmail(principal.getUsername());
	}

	@Override
	public Tenant getCompanyByUserLogged() throws NotFoundException {
		if (getUSerLogged().getTenant() == null) {
			throw new IllegalArgumentException(EMPRESA_ESTA_NULO);
		}

		return authGateway.buscarTenantPorId(getUSerLogged().getTenant().getId());
	}

	@Override
	public void validPasswordPolicies(String password) throws BusinessException {

		Matcher hasLetter = Pattern.compile("[a-z]").matcher(password);
		Matcher hasDigit = Pattern.compile("\\d").matcher(password);

		if (password.length() < 8 || !hasLetter.find() || !hasDigit.find()) {
			throw new BusinessException("password precisa ser mais complexo");
		}
	}

	@Override
	public void solicitaAtualizarPassword(String email) {
		Usuario usuario = authGateway.buscaUsuarioPorEmail(email);
		createRefreshToken(usuario);
		enviaEmailRefreshToken(usuario);
		authGateway.salvarUsuario(usuario);
	}

	private void enviaEmailRefreshToken(Usuario usuario) {
		StringBuilder str = new StringBuilder();

		str.append("http://localhost:4200/auth/update-password");
		str.append(";email=" + usuario.getEmail());
		str.append(";refreshtoken=" + usuario.getTokenRefreshPassword());
	}

	private void createRefreshToken(Usuario usuario) {
		String token = passwordEncoder.encode(usuario.getEmail() + usuario.getPassword() + LocalDateTime.now());
		usuario.ativaRefreshToken(token);
		authGateway.salvarUsuario(usuario);
	}
}
