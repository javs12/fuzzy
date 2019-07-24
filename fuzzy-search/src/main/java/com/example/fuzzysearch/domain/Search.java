package com.example.fuzzysearch.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class Search implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -6291211480975748246L;
  
  private String            search;
}
