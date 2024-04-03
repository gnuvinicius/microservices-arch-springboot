package com.garage.auth.domains.auth.service;

import java.util.List;
import java.util.UUID;

import com.garage.auth.domains.auth.models.Tenant;
import com.garage.auth.infraestructure.api.auth.dtos.TenantRequestDto;
import com.garage.auth.infraestructure.api.auth.dtos.UsuarioRequestDto;
import com.garage.auth.infraestructure.api.auth.dtos.UsuarioResponseDto;

public interface IManagerService {

	UsuarioResponseDto cadastraUsuario(UsuarioRequestDto dto) throws Exception;

	void arquiva(UUID id);

	List<UsuarioResponseDto> listaTodosUsuarios();

	List<Tenant> listaTodas() throws Exception;

	Tenant cadastraTenant(TenantRequestDto request) throws Exception;
}
