package com.garage.kbn.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kbn/api/v1")
public class KbnController {

  @GetMapping
  public String getAll() {
    return "working!";
  }
}
