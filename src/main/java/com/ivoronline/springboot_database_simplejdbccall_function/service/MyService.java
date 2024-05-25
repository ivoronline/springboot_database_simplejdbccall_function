package com.ivoronline.springboot_database_simplejdbccall_function.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.util.Map;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //==========================================================
  // CALL FUNCTION
  //==========================================================
  public String callFunction(String name) {

    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
      .withSchemaName  ("TEST")
      .withFunctionName("MY_FUNCTION")
      .declareParameters(
        new SqlParameter   ("name"   , Types.VARCHAR),
        new SqlOutParameter("message", Types.VARCHAR)
      );

    Map<String, Object> result = call.execute(name);
    String message = (String) result.get("message");

    return message;

  }
  
}
