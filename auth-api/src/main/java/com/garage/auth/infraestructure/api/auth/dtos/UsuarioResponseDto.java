package com.garage.auth.infraestructure.api.auth.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.garage.auth.domains.auth.models.Role;
import com.garage.auth.domains.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDto {

	private UUID id;
	private Status status;
	private String nome;
	private String email;
	private TenantDto tenant;
	private Set<Role> roles = new HashSet<>();
	private LocalDateTime ultimoAcesso;
	private LocalDateTime atualizadoEm;
}
