package com.garage.auth.domains.auth.service;

import com.garage.auth.domains.auth.models.Usuario;

public interface IRoleService {

	void addRoleAdmin(Usuario usuario);

	void addRoleCadastro(Usuario usuario);
}
