package com.betrybe.agrix;

import org.springframework.web.servlet.config.annotation.*;

public class CorsConfiguration  implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE");
  }
}