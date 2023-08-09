package com.gerensys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.criteria.Order;

@SpringBootApplication
@EntityScan(basePackages = "com.gerensys.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.gerensys.repository"})
@EnableTransactionManagement
@RestController
@EnableWebMvc
public class GerensysDevFinalApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(GerensysDevFinalApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
        registry.setOrder(Ordered.LOWEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scss/**", "/css/**", "/js/**", "/vendor/**")
                .addResourceLocations("classpath:/static/scss/",
                "classpath:/static/css/", "classpath:/static/js/", "classpath:/static/vendor/");
    }
}
