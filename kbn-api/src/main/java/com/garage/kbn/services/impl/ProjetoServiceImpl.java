package com.garage.kbn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.garage.kbn.repository.ProjetoRepository;
import com.garage.kbn.repository.schema.Projeto;
import com.garage.kbn.resources.dto.ProjetoDto;
import com.garage.kbn.services.ProjetoService;

@Component
public class ProjetoServiceImpl implements ProjetoService {

  @Autowired
  private ProjetoRepository repository;

  @Override
  public Projeto cadastraProjeto(ProjetoDto dto, String tenantId, String userId) {
    var projeto = new Projeto(dto.getTitulo(), tenantId, userId);

    return repository.save(projeto);
  }
  
}
