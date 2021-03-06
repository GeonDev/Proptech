package com.apt.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      //서버 응답시 json을 자바스크립트에서 처리할 수 있도록 허용
      config.setAllowCredentials(true);
      //모든 IP에 대하여 응답허용
      config.addAllowedOrigin("*");
      //모든 헤더에 대하여 응답 허용
      config.addAllowedHeader("*");
      //모든 http메서드에 대하여 요청허용  
      config.addAllowedMethod("*");

      source.registerCorsConfiguration("/api/**", config);
      return new CorsFilter(source);
   }

}
