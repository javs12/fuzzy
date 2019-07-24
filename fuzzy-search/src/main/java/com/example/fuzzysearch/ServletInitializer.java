
package com.example.fuzzysearch;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

  /**
   * Constructor por defecto de la clase.
   */
  public ServletInitializer() {
    super();
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(FuzzySearchApplication.class);
  }

}
