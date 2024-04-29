package com.garage.kbn.model.services;

import com.garage.kbn.model.schema.Projeto;
import com.garage.kbn.resources.dto.ProjetoDto;

public interface ProjetoService {
  Projeto cadastraProjeto(ProjetoDto dto);
}