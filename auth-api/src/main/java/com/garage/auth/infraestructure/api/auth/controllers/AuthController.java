package com.garage.auth.infraestructure.api.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garage.auth.domains.auth.service.IAuthService;
import com.garage.auth.infraestructure.api.auth.dtos.RequestRefreshPasswordDto;
import com.garage.auth.infraestructure.api.auth.dtos.UserLoginRequestDto;

@RestController
@Validated
@RequestMapping(value = "/auth/api/v1")
public class AuthController {

	@Autowired
	private IAuthService service;

	@PostMapping(value = "login")
	public ResponseEntity<?> auth(@RequestBody UserLoginRequestDto dto) throws AuthenticationException {
		return ResponseEntity.ok(service.auth(dto));
	}

	@GetMapping(value = "/reset-password")
	public ResponseEntity<?> requestUpdatePassword(@RequestParam(name = "login") String login) throws Exception {
		service.solicitaAtualizarPassword(login);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/reset-password")
	public ResponseEntity<?> updatePasswordByRefreshToken(@RequestBody RequestRefreshPasswordDto dto) throws Exception {
		service.updatePasswordByRefreshToken(dto);
		return ResponseEntity.ok().build();
	}
}
