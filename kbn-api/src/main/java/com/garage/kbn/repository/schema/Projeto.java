package com.garage.kbn.repository.schema;

import java.time.LocalDateTime;
import java.util.UUID;

import com.garage.kbn.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_projetos")
public class Projeto {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private Status status;
  private UUID tenantId;
  private UUID userId;
  private String titulo;
  private LocalDateTime criadoEm;
  private LocalDateTime atualizadoEm;


  public Projeto(String titulo, String tenantId, String userId) {
    this.titulo = titulo;
    this.tenantId = UUID.fromString(tenantId);
    this.userId = UUID.fromString(userId);
    this.criadoEm = LocalDateTime.now();
    this.status = Status.ATIVO;
  }

}
