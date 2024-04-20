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

import com.garage.kbn.repository.schema.Projeto;
import com.garage.kbn.resources.dto.ProjetoDto;
import com.garage.kbn.services.ProjetoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/kbn/api/v1")
public class KbnResource {

  private static final String TENANT_ID = "tenantId";
  private static final String USER_ID = "userId";
  @Autowired
  private ProjetoService projetoService;

  @GetMapping
  @PreAuthorize("hasRole('USER')")
  public String getAll(HttpServletRequest request) {
    return "working!";
  }

  @PostMapping("/projetos")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> cadastraProjeto(HttpServletRequest request, @RequestBody ProjetoDto dto)
      throws URISyntaxException {

    if (request.getAttribute(TENANT_ID) != null && request.getAttribute(USER_ID) != null) {
      String tenantId = request.getAttribute(TENANT_ID).toString();
      String userId = request.getAttribute("userId").toString();
      Projeto entity = projetoService.cadastraProjeto(dto, tenantId, userId);
      return ResponseEntity.created(new URI(entity.getId().toString())).build();
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

}
