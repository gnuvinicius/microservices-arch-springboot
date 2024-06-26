package com.garage.auth.domains.auth.gateway;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.garage.auth.domains.auth.models.Role;
import com.garage.auth.domains.auth.models.Tenant;
import com.garage.auth.domains.auth.models.Usuario;
import com.garage.auth.exceptions.NotFoundException;

public interface IAuthGateway {

    Usuario buscaUsuarioPorEmail(String email) throws UsernameNotFoundException;
    void salvarUsuario(Usuario usuario);
    Tenant buscarTenantPorId(UUID tenantId) throws NotFoundException;
    Usuario buscarUsuarioPorId(UUID id) throws NotFoundException;
    List<Usuario> buscarUsuariosPorTenant(Tenant tenant);
    List<Tenant> buscarTenants();
    void salvarTenant(Tenant tenant);
    void removeTenant(Tenant tenant);
    Role busarRolePorRoleName(String roleName);
    Tenant buscarTenantPorCpfCnpj(String cnpj);
}
