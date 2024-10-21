package com.example.usuario_cadastro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") // Permite todas as requisições para todos os endpoints
				.allowedOrigins("http://localhost:4200") // Altere para o endereço do seu frontend
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
				.allowedHeaders("*") // Permite todos os cabeçalhos
				.allowCredentials(true); // Permite credenciais (cookies, cabeçalhos de autorização)
	}
}
