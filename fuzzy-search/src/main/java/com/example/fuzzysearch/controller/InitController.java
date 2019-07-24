package com.example.fuzzysearch.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.fuzzysearch.helper.InitHelper;

@RestController
public class InitController extends InitHelper {

  /**
   * 
   */
  private static final long serialVersionUID = -2173851469187904490L;

  public InitController() {
    super();
  }

  @GetMapping("/home")
  public String home() {
    return "Bienvenido";
  }

  @PostMapping("/add")
  public String add(@RequestBody String json) {
  
    return this.addPerson(json);

  }

  @GetMapping("/list")
  public Object getListPerson() {    
    return new ResponseEntity<>(this.getList(), HttpStatus.OK);
  }
  
  @GetMapping("/search")
  public Object searchPerson(@RequestBody String json) {    
    return new ResponseEntity<>(this.searchByName(json), HttpStatus.OK);
  }
  

}
