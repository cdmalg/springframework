package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spring")
public class SpringController {

  @RequestMapping(value = "test", method = {RequestMethod.GET})
  public String test(HttpServletRequest request, ModelMap map) {
    String sessionId = request.getSession().getId();
    map.put("sessionId",sessionId);
    map.put("port",request.getLocalPort());
    return "index";
  }
}
