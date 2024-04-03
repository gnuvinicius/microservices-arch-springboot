package com.garage.auth.domains.auth.service.impl;

import org.springframework.stereotype.Service;

import com.garage.auth.domains.auth.gateway.IAuthGateway;
import com.garage.auth.domains.auth.models.Role;
import com.garage.auth.domains.auth.models.Usuario;
import com.garage.auth.domains.auth.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	private IAuthGateway authGateway;

	public RoleService(IAuthGateway authGateway) {
		this.authGateway = authGateway;
	}

	@Override
	public void addRoleAdmin(Usuario usuario) {
		Role adminRole = authGateway.busarRolePorRoleName("ROLE_ADMIN");
		usuario.getRoles().add(adminRole);
	}

	@Override
	public void addRoleCadastro(Usuario usuario) {
		Role adminRole = authGateway.busarRolePorRoleName("ROLE_USER");
		usuario.getRoles().add(adminRole);
	}
}
