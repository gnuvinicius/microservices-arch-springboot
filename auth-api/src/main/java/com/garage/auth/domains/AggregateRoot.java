package com.garage.auth.domains;

import java.time.LocalDateTime;
import java.util.UUID;

import com.garage.auth.domains.auth.models.Tenant;
import com.garage.auth.domains.enums.Status;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AggregateRoot {

	@Id
	protected UUID id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenant_id", nullable = false)
	protected Tenant tenant;

	protected LocalDateTime criadoEm;
	protected LocalDateTime atualizadoEm;
	protected Status status;

	protected AggregateRoot() {
		this.id = UUID.randomUUID();
		this.criadoEm = LocalDateTime.now();
		this.atualizadoEm = LocalDateTime.now();
		this.status = Status.ATIVO;
	}
}
