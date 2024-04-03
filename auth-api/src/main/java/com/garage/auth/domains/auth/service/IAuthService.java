package com.garage.auth.domains.auth.service;

import com.garage.auth.domains.auth.models.Tenant;
import com.garage.auth.domains.auth.models.Usuario;
import com.garage.auth.exceptions.BusinessException;
import com.garage.auth.exceptions.NotFoundException;
import com.garage.auth.infraestructure.api.auth.dtos.RequestRefreshPasswordDto;
import com.garage.auth.infraestructure.api.auth.dtos.TokenDto;
import com.garage.auth.infraestructure.api.auth.dtos.UserLoginRequestDto;

public interface IAuthService {

	TokenDto auth(UserLoginRequestDto dto);

	void updatePasswordByRefreshToken(RequestRefreshPasswordDto dto) throws BusinessException;

	Usuario getUSerLogged();

	Tenant getCompanyByUserLogged() throws NotFoundException;

	void validPasswordPolicies(String password);

	void solicitaAtualizarPassword(String login);
}
