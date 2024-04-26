package com.garage.kbn.model.schema;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tb_projetos")
public class Projeto extends AggregateRoot {

  private String titulo;

  @OneToMany(mappedBy = "projeto")
  private List<Task> tasks;

  public Projeto(String titulo, String tenantId, String userId) {
    super(tenantId, userId);
    this.titulo = titulo;
  }

}
