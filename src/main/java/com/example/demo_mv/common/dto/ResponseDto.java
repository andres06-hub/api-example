package com.example.demo_mv.common.dto;

import lombok.Data;

@Data
public class ResponseDto {
  
  private Boolean status;
  private String message = null;
  private Object data = null;

  public ResponseDto(Boolean status){
    this.status = status;
  }

  public ResponseDto(Boolean status, String message, Object data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }
}
