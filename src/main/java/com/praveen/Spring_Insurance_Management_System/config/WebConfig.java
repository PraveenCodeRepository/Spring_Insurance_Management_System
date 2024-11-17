package com.praveen.Spring_Insurance_Management_System.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow cross-origin requests from localhost:3000 (React development server)
        registry.addMapping("/**")          // Apply to all paths
                .allowedOrigins("http://localhost:3000")   // Your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")        // Allow all headers
                .allowCredentials(true);    // Allow credentials (cookies, auth headers, etc.)
    }


}
