package ru.mail.dimaushenko.webmodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/users").hasAnyRole(UserRoleEnumDTO.ADMINISTRATOR.name())
                .antMatchers("/users/add").hasAnyRole(UserRoleEnumDTO.ADMINISTRATOR.name())
                .antMatchers("/comments").hasAnyRole(UserRoleEnumDTO.ADMINISTRATOR.name())
                .antMatchers("/comments/*/delete").hasAnyRole(UserRoleEnumDTO.ADMINISTRATOR.name())
                .antMatchers("/profile").hasAnyRole(UserRoleEnumDTO.CUSTOMER_USER.name())
                .antMatchers("/articles").hasAnyRole(UserRoleEnumDTO.CUSTOMER_USER.name())
                .antMatchers("/api/users").hasAnyRole(UserRoleEnumDTO.SECURE_API_USER.name())
                .antMatchers("/api/articles").hasAnyRole(UserRoleEnumDTO.SECURE_API_USER.name())
                .antMatchers("/api/articles/**").hasAnyRole(UserRoleEnumDTO.SECURE_API_USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}
