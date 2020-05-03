package ru.mail.dimaushenko.webmodule.config;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.util.unit.DataSize.ofKilobytes;

@Configuration
public class MultipartConfig {
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(ofKilobytes(128));
        factory.setMaxRequestSize(ofKilobytes(128));
        return factory.createMultipartConfig();
    }
}
