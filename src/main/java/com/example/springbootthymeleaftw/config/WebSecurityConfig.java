package com.example.springbootthymeleaftw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {
    private static final String[] METHODS_ALLOWED = {
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name(),
    };

//    @Autowired
//    public void configureGlobal (AuthenticationManagerBuilder authMgr) throws Exception {
//        authMgr.inMemoryAuthentication()
//                .withUser("b2b").authorities("ROLE_B2B")
//                .and()
//                .withUser("b2c").authorities("ROLE_B2C");
//    }

    @Bean
    SecurityFilterChain resources (HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers( "/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                //.failureUrl("/login-error") //if you want a separate page for failed auth.
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/b2c/*")
                .hasRole("B2C")
                .antMatchers("/b2b/*")
                .hasRole("B2B")
                .antMatchers("/client/*")
                .hasRole("CLIENT")
                .and()
                .build();

//        return http
//                .authorizeRequests()
//                .antMatchers( "/login", "/", "logout")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/client", "/client/*", "/client*")
//                .hasRole("Client")
//                //.and()
//                //.authorizeRequests()
//                .antMatchers("/b2c", "/b2c/*", "/b2c*")
//                .hasRole("B2C")
//                .antMatchers("/b2b", "/b2b*", "/b2b/*")
//                .hasRole("B2B")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(METHODS_ALLOWED)
                .allowedOrigins("*");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
