package com.example.demo_mv.common.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

public class BcryptService {

  public String BcryptData(String data) {
    return BCrypt.withDefaults().hashToString(12, data.toCharArray());
  }

  public Result verifyData(String data, String dataHash) {
    return BCrypt.verifyer().verify(data.toCharArray(), dataHash);
  }
  
}
