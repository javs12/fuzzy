package com.example.fuzzysearch.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class Person implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 379596634551159509L;

  private String name;
}
