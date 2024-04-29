package com.garage.kbn.model.schema;

import java.time.LocalDateTime;
import java.util.UUID;

import com.garage.kbn.enums.Status;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AggregateRoot {

	@Id
	protected UUID id;
	protected UUID tenantId;
	protected UUID userId;
	protected LocalDateTime criadoEm;
	protected LocalDateTime atualizadoEm;
	protected Status status;

	protected AggregateRoot(String tenantId, String userId) {
		this.criadoEm = LocalDateTime.now();
		this.atualizadoEm = LocalDateTime.now();
		this.status = Status.ATIVO;
		this.tenantId = UUID.fromString(tenantId);
    this.userId = UUID.fromString(userId);
	}
}
