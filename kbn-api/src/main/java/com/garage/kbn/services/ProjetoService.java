package com.garage.kbn.services;

import com.garage.kbn.repository.schema.Projeto;
import com.garage.kbn.resources.dto.ProjetoDto;

public interface ProjetoService {

  Projeto cadastraProjeto(ProjetoDto dto);

}
