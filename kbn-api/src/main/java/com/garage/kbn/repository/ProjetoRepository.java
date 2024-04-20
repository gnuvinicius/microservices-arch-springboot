package com.garage.kbn.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garage.kbn.repository.schema.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {
  
}
