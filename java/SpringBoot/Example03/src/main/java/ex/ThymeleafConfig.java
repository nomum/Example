package ex;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.thymeleaf.TemplateEngine;

import ex.utility.MyTLDialect;

@Configuration
public class ThymeleafConfig{

    @Autowired
    public TemplateEngine templateEngine; 
    
    @Bean
    public TemplateEngine addDialect() {
        templateEngine.addDialect(
            new MyTLDialect()
        );

        return templateEngine;
    }
}