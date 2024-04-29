package com.garage.kbn.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.garage.kbn.model.schema.Projeto;
import com.garage.kbn.model.services.ProjetoService;
import com.garage.kbn.repository.ProjetoRepository;
import com.garage.kbn.resources.dto.ProjetoDto;
import com.garage.kbn.shared.JwtRequestAttributes;

import jakarta.servlet.ServletContext;

@Component
public class ProjetoServiceImpl implements ProjetoService {

  @Autowired
	private ServletContext servletContext;

  @Autowired
  private ProjetoRepository repository;

  @Override
  public Projeto cadastraProjeto(ProjetoDto dto) {

    var request = (JwtRequestAttributes)servletContext.getAttribute("requestAtt");
    var projeto = new Projeto(dto.getTitulo(), request.getTenantId(), request.getUserId());

    return repository.save(projeto);
  }
  
}
