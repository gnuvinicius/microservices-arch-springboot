package com.garage.kbn.config;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class RequestAttr implements Serializable {
   
  private String tenantId;
  private String userId;

  public RequestAttr(String tenantId, String userId) {
    this.tenantId = tenantId;
    this.userId = userId;
  }
  
}
