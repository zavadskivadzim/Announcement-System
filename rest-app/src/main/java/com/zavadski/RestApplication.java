package com.zavadski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"com.zavadski"})
@PropertySource({"classpath:dao.properties"})
@SpringBootApplication
public class RestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
