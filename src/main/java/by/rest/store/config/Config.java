package by.rest.store.config;

import by.rest.store.model.Order;
import by.rest.store.model.Pet;
import by.rest.store.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@Configuration
public class Config {

     @Bean
     public MethodValidationPostProcessor methodValidationPostProcessor() {
          return new MethodValidationPostProcessor();
     }

     @Bean
     public Map<String, User> usersList() {
          return new HashMap<>();
     }

     @Bean
     public Map<Long, Long> tokens() {
          return new HashMap<>();
     }

     @Bean
     public Map<Long, Pet> petsList() { return new HashMap<>(); }

     @Bean
     public Map<Long, Order> orders() {
          return new HashMap<>();
     }
}
