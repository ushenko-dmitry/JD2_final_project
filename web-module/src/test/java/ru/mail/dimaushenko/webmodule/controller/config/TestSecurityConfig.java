package ru.mail.dimaushenko.webmodule.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "ru.mail.dimaushenko.repository",
    "ru.mail.dimaushenko.service",
    "ru.mail.dimaushenko.webmodule"
})
public class TestSecurityConfig {

}
