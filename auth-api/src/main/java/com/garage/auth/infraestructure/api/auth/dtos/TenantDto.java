package com.garage.auth.infraestructure.api.auth.dtos;

import java.time.LocalDateTime;

import com.garage.auth.domains.enums.Status;

public record TenantDto(Long id, String nome,
        String endereco,
        String cnpj,
        Status status,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm) {
}