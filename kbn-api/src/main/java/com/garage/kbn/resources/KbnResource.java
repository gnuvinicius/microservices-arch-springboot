package com.garage.kbn.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.kbn.model.schema.Projeto;
import com.garage.kbn.model.services.ProjetoService;
import com.garage.kbn.resources.dto.ProjetoDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/kbn/api/v1")
public class KbnResource {

  @Autowired
  private ProjetoService projetoService;

  @GetMapping
  @PreAuthorize("hasRole('USER')")
  public String getAll(HttpServletRequest request) {
    return "working!";
  }

  @PostMapping("/projetos")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> cadastraProjeto(@RequestBody ProjetoDto dto)
      throws URISyntaxException {

      Projeto entity = projetoService.cadastraProjeto(dto);
      return ResponseEntity.created(new URI(entity.getId().toString())).build();
  }

}
