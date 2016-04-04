package net.zenkevich.alexander.callstoday.controller;

import net.zenkevich.alexander.callstoday.model.Call;
import net.zenkevich.alexander.callstoday.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest controller.
 */
@RestController
@RequestMapping("/call")
public class CallController {

  @Autowired
  private CallService callService;

  /**
   * Add call
   *
   * @param call   object to save
   * @param result binding result
   * @return saved object
   */
  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Object add(@Valid Call call, BindingResult result) {
    if (result.hasErrors()) {
      List<Map<String, Object>> responseEntity = buildErrorResponse(result);
      return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
    }
    return callService.add(call);
  }

  private List<Map<String, Object>> buildErrorResponse(BindingResult result) {
    List<Map<String, Object>> responseEntity = new ArrayList<>(result.getFieldErrors().size());
    for (FieldError error : result.getFieldErrors()) {
      Map<String, Object> map = new HashMap<>();
      map.put("message", error.getDefaultMessage());
      map.put("field", error.getField());
      map.put("value", error.getRejectedValue());
      responseEntity.add(map);
    }
    return responseEntity;
  }

}
