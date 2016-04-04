package net.zenkevich.alexander.callstoday.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main controller
 */
@Controller
public class MainController {

  /**
   * Main page with rest api
   *
   * @return model
   */
  @RequestMapping(value = "/api", method = RequestMethod.GET)
  public ModelAndView home() {
    return new ModelAndView("main");
  }

}
