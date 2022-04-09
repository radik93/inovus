package ru.inovus.jasonxml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@SpringBootApplication
public class JasonxmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasonxmlApplication.class, args);
    }

}
